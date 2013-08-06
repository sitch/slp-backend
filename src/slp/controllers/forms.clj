(ns slp.controllers.forms
  (:require [datomic.api :as d]
            [slp.db.query :as db]
            [slp.db.maprules :as mr]
            [slp.db.transactions :as ts]
            [slp.db.validations :as validations]
            [flyingmachine.cartographer.core :as c])
  (:use [liberator.core :only [defresource]]
        slp.controllers.shared
        slp.models.partners
        slp.utils))

(defresource show [params]
  :available-media-types ["application/json"]
;  :exists? (exists? (record (id) {:include {:posts {:include {:topic {:only [:title :id]}}}}}))
  :handle-ok record-in-ctx)

(defresource show-schema []
  :available-media-types ["application/json"]
;  :exists? (exists? (record (id) {:include {:posts {:include {:topic {:only [:title :id]}}}}}))
  :handle-ok record-in-ctx)

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