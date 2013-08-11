(ns slp.ws.app
  (:gen-class)
  (:require [slp.ws.server :as server]
            [slp.ws.db.manage :as db]))

(defn shutdown 
  []
  (do (println "Shutting down..")
      (System/exit 0)))

(defn -main
  [cmd]
  (cond 
   (= cmd "server") (server/-main)
   (= cmd "db/reload") (do (println (db/reload)) (shutdown))
   (= cmd "db/migrate") (do (println (db/migrate)) (shutdown))
   :else (do (println (str "Invalid option: [" cmd "]") (shutdown)))))