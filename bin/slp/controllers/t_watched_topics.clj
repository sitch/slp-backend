(ns slp.controllers.t-watched-topics
  (:require [slp.db.test :as tdb]
            [slp.db.query :as q]
            [slp.db.manage :as db-manage]
            [slp.controllers.watched-topics :as watched-topics])
  (:use midje.sweet
        slp.paths
        slp.controllers.test-helpers))

(setup-db-background)

(fact "query returns all watched topics"
  (response-data :get "/watched-topics" {} (auth))
  => (one-of map?))