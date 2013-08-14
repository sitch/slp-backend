(ns slp.controllers.shared
  (:require [slp.db.query :as db])
  (:use [flyingmachine.webutils.validation :only (if-valid)]
        slp.models.permissions
        slp.db.mapification
        slp.utils))

(def owner-inclusion-options
  {:owner {:only [:id :username :gravatar]}})

(defn invalid
  [errors]
  {:body {:errors errors}
   :status 412})

(defmacro id
  []
  '(str->int (:id params)))

(defmacro validator
  "Used in malformed? which is why truth values are reversed"
  [params validation]
  `(fn [ctx#]
     (if-valid
      ~params ~validation errors#
      false
      [true {:errors errors#
             :representation {:media-type "application/json"}}])))


;; working with liberator
(defn exists?
  [record]
  (if record
    {:record record}))

(defn record-in-ctx
  [ctx]
  (get ctx :record))

(def exists-in-ctx? record-in-ctx)

(defn errors-in-ctx
  [ctx]
  {:errors (get ctx :errors)})

(defn delete-record-in-ctx
  [ctx]
  (db/t [{:db/id (get-in ctx [:record :id])
          :content/deleted true}]))

(defmacro can-delete-record?
  [record auth]
  `(fn [_#]
     (let [record# ~record
           auth# ~auth]
       (if (owner? record# auth#)
         {:record record#}))))

(defmacro can-update-record?
  [record auth]
  `(fn [_#]
     (let [auth# ~auth
           record# ~record]
       (if (and (not (:deleted record#))
                (owner? record# auth#))
         {:record record#}))))

(defn create-record
  [creation-fn params mapifier]
  (fn [_]
    (let [result (creation-fn params)]
      {:record (mapify-tx-result result mapifier)})))

(defn create-content
  [creation-fn params auth mapifier]
  (create-record creation-fn (merge params {:owner-id (:id auth)}) mapifier))