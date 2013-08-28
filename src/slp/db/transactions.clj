(ns slp.db.transactions
  (:require [datomic.api :as d]
            [slp.db.maprules :as mr]
            [slp.db.tools.query :as db]
            [flyingmachine.cartographer.core :as c])
  (:use slp.utils))

(defn create-user
  [params]
  (let [params (remove-nils-from-map (c/mapify params mr/user->txdata))]
    {:result (db/t [params])
     :tempid (:db/id params)}))

(defn update-profile
  [params]
  (let [params (remove-nils-from-map (c/mapify params mr/profile->txdata))]
    {:result (db/t [params])
     :tempid (:db/id params)}))