(ns slp.controllers.analytics)
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
;(defresource create! [params auth]
;  :allowed-methods [:post]
;  :available-media-types ["application/json"]
;  :authorized? (logged-in? auth)
;
;  :malformed? (validator params (:create analytics/post))
;  :handle-malformed errors-in-ctx
;
;  :post! (create-content ts/create-event params auth record)
;  :handle-created record-in-ctx)
