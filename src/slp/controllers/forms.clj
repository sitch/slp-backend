(ns slp.controllers.forms
  (:require [datomic.api :as d]
            [slp.db.validations :as validations]
            [slp.db.query :as db]
            [slp.db.maprules :as mr]
            [slp.db.transactions :as ts]
            [slp.models.formschema :as schema]
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
  :handle-ok schema/full-form)

(defresource show-progress [params]
  :available-media-types ["application/json"]
  :handle-ok schema/full-form)

;(defresource create! [params]
;  :available-media-types ["application/json"]
;  :handle-ok schema/full-form)
;
;(defresource update! [params]
;  :available-media-types ["application/json"]
;  :handle-ok schema/full-form)