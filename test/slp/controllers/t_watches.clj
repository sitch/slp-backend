(ns slp.controllers.t-watches
  (:require [slp.db.test :as tdb]
            [slp.db.query :as q]
            [slp.db.manage :as db-manage]
            [slp.controllers.watches :as watches])
  (:use midje.sweet
        slp.paths
        slp.controllers.test-helpers))

(setup-db-background)

(defn watch
  ([]
     (watch (:id (auth))))
  ([userid]
     (q/one [:watch/user userid])))

(fact "creating a watch results in success"
  (let [auth (auth "joebob")
        response (res :post "/watches" {:topic-id (topic-id) :user-id (:id auth)} auth)]
    (:db/id (watch (:id auth))) =not=> nil?
    response => (contains {:status 201})))

(fact "deleting a watch as the creator results in success"
  (let [response (res :delete (str "/watches/" (:db/id (watch))) nil (auth "flyingmachine"))]
    (watch) => nil
    response => (contains {:status 204})))