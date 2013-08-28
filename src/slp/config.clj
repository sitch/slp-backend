(ns slp.config
  (:require [slp.utils :as utils])
  (:use environ.core))

(def conf
  (let [environment (or (env :app-env) "development")]
    (merge
     {:server-port 80
      :cookie-name "slp-session"
      :datomic {:db-uri "datomic:free://localhost:4334/slp"
                :test-uri "datomic:mem://slp"}
      :schema :slp/schema
      :migrations [:20130521-161013-schema
                   :20130521-161014-seed-data]}
     (utils/read-resource (str "config/environments/" environment ".edn")))))

(defn config
  [& keys]
  (get-in conf keys))