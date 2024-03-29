(ns slp.app
  (:gen-class)
  (:require [slp.server :as server]
            [slp.db.tools.manage :as db]))

(defn shutdown 
  []
  (do (println "Successfully applied migration..")
      (System/exit 0)))

(defn -main
  [cmd]
  (cond 
   (= cmd "server") (server/-main)
   (= cmd "db/reload") (do (println (db/reload)) (shutdown))
   (= cmd "db/migrate") (do (println (db/migrate)) (shutdown))))