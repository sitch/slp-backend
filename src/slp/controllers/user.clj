(ns slp.controllers.user
  (:require [datomic.api :as d]
			      [slp.controllers.shared :as shared]
            [slp.db.validations :as validations]
            [slp.db.maprules :as mr]
            [slp.db.transactions :as ts]
            [slp.db.tools.query :as db]
			      [slp.db.tools.mapification :as mapi]
            [slp.models.permissions :as perms]
			      [slp.utils :as utils]
            [flyingmachine.cartographer.core :as c]
            [cemerick.friend :as friend]
            [cemerick.friend.workflows :as workflows])
  (:use [flyingmachine.webutils.validation :only (if-valid)]
        [liberator.core :only [defresource]]))

(mapi/defmapifier record     mr/ent->user)
(mapi/defmapifier authrecord mr/ent->userauth)

(defn attempt-registration
  [req]
  (let [{:keys [uri request-method params session]} req]
    (when (and (= uri "/api/user")
               (= request-method :post))
      (if-valid
       params (:create validations/user) errors
       (workflows/make-auth
        (mapi/mapify-tx-result (ts/create-user params) record)
        {:cemerick.friend/redirect-on-auth? false})
       (shared/invalid errors)))))

(defn registration-success-response
  [params auth]
  "If the request gets this far, it means that user registration was successful."
  (if auth {:body auth}))

(defresource show [params]
  :allowed-methods [:get]
  :available-media-types ["application/json"]
  
;  :authorized? (perms/current-user-id? (shared/get-id params) auth)
  
;  :exists? (exists? (record (shared/get-id params) {:include {:posts {:include {:topic {:only [:title :id]}}}}}))
  :handle-ok shared/record-in-ctx)

(defn update!*
  [params]
  (db/t [(utils/remove-nils-from-map
          (c/mapify
           params
           mr/user->txdata
           {:exclude [:user/username :user/password]}))]))

(defresource update! [params auth]
  :allowed-methods [:put :post]
  :available-media-types ["application/json"]

  :malformed? (shared/validator params (validations/email-update auth))
  :handle-malformed shared/errors-in-ctx

  :authorized? (perms/current-user-id? (shared/get-id params) auth)
  :exists? shared/record-in-ctx
  
  :post! (fn [_] (update!* params))
  :new? false
  :respond-with-entity? true
  :handle-ok (fn [_] (record (shared/get-id params))))

(defn- password-params
  [params]
  (let [user (authrecord (shared/get-id params))]
    {:new-password (select-keys params [:new-password :new-password-confirmation])
     :current-password (merge (select-keys params [:current-password]) {:password (:password user)})}))

(defresource change-password! [params auth]
  :allowed-methods [:put :post]
  :available-media-types ["application/json"]

  :malformed? (shared/validator (password-params params) validations/change-password)
  :handle-malformed shared/errors-in-ctx
  
  :authorized? (fn [_] (perms/current-user-id? (shared/get-id params) auth))
  
  :post! (fn [_] (db/t [(c/mapify params mr/change-password->txdata)])))