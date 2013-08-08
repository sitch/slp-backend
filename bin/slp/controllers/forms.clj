(ns slp.controllers.forms
  (:require [slp.db.validations :as validations]
            [slp.db.query :as db]
            [datomic.api :as d]
            [slp.db.maprules :as mr]
            [slp.db.transactions :as ts]
            [slp.models.signup :as signup]
            [flyingmachine.cartographer.core :as c]
            [cemerick.friend :as friend]
            cemerick.friend.workflows)
  (:use [flyingmachine.webutils.validation :only (if-valid)]
        [liberator.core :only [defresource]]
        slp.models.permissions
        slp.db.mapification
        slp.controllers.shared
        slp.utils))

(defresource show-schema [params]
  :available-media-types ["application/json"]
  :handle-ok signup/full-form)

(defresource show-progress [params]
  :available-media-types ["application/json"]
  :handle-ok signup/full-form)


;  (:require [datomic.api :as d]
;            [slp.db.query :as db]
;            [slp.db.maprules :as mr]
;            [slp.db.transactions :as ts]
;            [slp.db.validations :as validations]
;            [flyingmachine.cartographer.core :as c])
;  (:use [liberator.core :only [defresource]]
;        slp.controllers.shared
;        slp.models.partners
;        slp.utils))
;
;(defresource show [params]
;  :available-media-types ["application/json"]
;  :exists? (exists? (record (id) {:include {:posts {:include {:topic {:only [:title :id]}}}}}))
;  :handle-ok record-in-ctx)

;(defresource show-schema []
;  :available-media-types ["application/json"]
;  :exists? (exists? (record (id) {:include {:posts {:include {:topic {:only [:title :id]}}}}}))
;  :handle-ok record-in-ctx)

;(def wrap-form [user form]
;  (loop form slp.models.partners/:user))

;(defn build [id]
;  (db/one :partnerMap))

;(def partners (ns-publics slp.models.partners))
;
;(defn extract-partner [partner] nil)
;
;(defn fold-in-partner [form partner]
;  merge form (extract-form partner))
;  
;(def full-form (reduce fold-in-partner partners))