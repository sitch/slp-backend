(ns slp.db.t-db-functions
  (:require [slp.db.test :as tdb]
            [slp.db.maprules :as mr]
            [flyingmachine.cartographer.core :as c]
            [slp.db.query :as q])
  (:use midje.sweet
        slp.controllers.test-helpers))

(setup-db-background)

(defn watch
  []
  (q/one [:watch/topic]))

(fact "increment-register"
  (q/t [[:increment-watch-count (-> (watch) :watch/topic :db/id) 1]])
  (:watch/unread-count (watch))
  => 1)