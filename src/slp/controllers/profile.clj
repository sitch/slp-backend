(ns slp.controllers.profile
  (:require [datomic.api :as d]
            [slp.db.maprules :as mr]
            [slp.db.transactions :as ts]
            [slp.db.validations :as validations]
            [slp.db.tools.query :as db]
			      [slp.db.tools.mapification :as mapi]
            [slp.controllers.shared :as shared]
            [slp.models.permissions :as perms])
  (:use [flyingmachine.webutils.validation :only (if-valid)]
        [liberator.core :only [defresource]]))



;(mapi/defmapifier record
;  mr/ent->profile
;  {:include author-inclusion-options
;   ;; :exclude [:content :created-at :topic-id]
;   })

(defn query-profile
  [params]
  (d/q '[:find ?e
         :where [?e :user/username ?userid]]
       (db/db) "admin"))
;        :where [?e :user/id ?userid]]
;       (db/db) (shared/get-id params)))

(defn query-all-profile
  [params]
  (d/entity (db/db) (query-profile params)))

(defresource show [params auth]
  :allowed-methods [:get]
  :available-media-types ["application/json"]
  
  :authorized? (perms/current-user-id? (shared/get-id params) auth)
  :handle-ok (mr/ent->profile (query-all-profile params)))


;(defresource update! [params auth]
;  :allowed-methods [:put]
;  :available-media-types ["application/json"]
;
;  :malformed? (shared/validator params (:update validations/post))
;  :handle-malformed shared/errors-in-ctx
;
;  :authorized? (perms/can-update-record? (record (id)) auth)
;  :exists? shared/record-in-ctx
;  :put! (ts/update-post params)
;  :post! (ts/update-post params)
;  :new? false
;  :respond-with-entity? true
;  :handle-ok (fn [_] (record (id)))
;  )