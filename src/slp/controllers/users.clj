(ns slp.controllers.users
  (:require [slp.db.validations :as validations]
            [slp.db.query :as db]
            [datomic.api :as d]
            [slp.db.maprules :as mr]
            [slp.db.transactions :as ts]
            [flyingmachine.cartographer.core :as c]
            [cemerick.friend :as friend]
            cemerick.friend.workflows)
  (:use [flyingmachine.webutils.validation :only (if-valid)]
        [liberator.core :only [defresource]]
        slp.models.permissions
        slp.db.mapification
        slp.controllers.shared
        slp.utils))

(defmapifier record mr/ent->user)
(defmapifier authrecord mr/ent->userauth)

(defn attempt-registration
  [req]
  (let [{:keys [uri request-method params session]} req]
    (when (and (= uri "/users")
               (= request-method :post))
      (if-valid
       params (:create validations/user) errors
       (cemerick.friend.workflows/make-auth
        (mapify-tx-result (ts/create-user params) record)
        {:cemerick.friend/redirect-on-auth? false})
       (invalid errors)))))

(defn registration-success-response
  [params auth]
  "If the request gets this far, it means that user registration was successful."
  (if auth {:body auth}))

(defresource show [params]
  :available-media-types ["application/json"]
  :exists? (exists? (record (id) {:include {:posts {:include {:topic {:only [:title :id]}}}}}))
  :handle-ok record-in-ctx)

(defn update!*
  [params]
  (db/t [(remove-nils-from-map
          (c/mapify
           params
           mr/user->txdata
           {:exclude [:user/username :user/password]}))]))

(defresource update! [params auth]
  :allowed-methods [:put :post]
  :available-media-types ["application/json"]

  :malformed? (validator params (validations/email-update auth))
  :handle-malformed errors-in-ctx

  :authorized? (current-user-id? (id) auth)
  :exists? record-in-ctx
  
  :post! (fn [_] (update!* params))
  :new? false
  :respond-with-entity? true
  :handle-ok (fn [_] (record (id))))


(defn- password-params
  [params]
  (let [user (authrecord (id))]
    {:new-password (select-keys params [:new-password :new-password-confirmation])
     :current-password (merge (select-keys params [:current-password]) {:password (:password user)})}))

(defresource change-password! [params auth]
  :allowed-methods [:put :post]
  :available-media-types ["application/json"]

  :malformed? (validator (password-params params) validations/change-password)
  :handle-malformed errors-in-ctx
  
  :authorized? (fn [_] (current-user-id? (id) auth))
  
  :post! (fn [_] (db/t [(c/mapify params mr/change-password->txdata)])))