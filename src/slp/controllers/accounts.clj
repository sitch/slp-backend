(ns slp.controllers.accounts
  (:require [flyingmachine.cartographer.core :as c]
            [slp.db.validations :as validations]
            [slp.db.query :as db]
            [slp.db.transactions :as ts]
            [slp.db.maprules :as mr]
            [slp.controllers.shared :as shared]
		        [slp.models.permissions :as perms]
		        [slp.db.mapification :as map]
		        [slp.utils] :as utils)
  (:use [flyingmachine.webutils.validation :only (if-valid)]
        [liberator.core :only (defresource)]))

;(defn create-account [params auth]
;  (ts/create-account (merge params auth)))
;
;(defn can-modify-account? [params auth]
;  (db/can-modify-account (merge params auth)))
;
;(defresource create! [params auth]
;  :allowed-methods [:post]
;  :available-media-types ["application/json"]
;  :authorized? (can-modify-account? params auth)
;  
;  :malformed? (shared/validator params (:create validations/account))
;  :handle-malformed shared/errors-in-ctx
;  
;  :exists? (db/verify-account params auth)
;  
;  :post! (create-account params auth)
;  :handle-created shared/record-in-ctx)
;
;(defresource update! [params auth]
;  :allowed-methods [:put]
;  :available-media-types ["application/json"]
;  :authorized? (perms/logged-in? auth)
;  
;  :malformed? (shared/validator params (:update validations/account))
;  :handle-malformed shared/errors-in-ctx
;  
;  :exists? shared/record-in-ctx
;  :put! (ts/update-account params)
;  :new? false
;  :respond-with-entity? true
;  :handle-ok (fn [_] (record (id))))
;
;(defresource delete! [params auth]
;  :allowed-methods [:delete]
;  :available-media-types "application/json"
;  :authorized? (can-modify-account? params auth)
;  :exists? shared/exists-in-ctx
;  :delete! shared/delete-record-in-ctx)
  