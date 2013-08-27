(ns slp.config
  (:use environ.core
        slp.utils))

(def conf
  (let [environment (or (env :app-env) "development")]
    (merge
     {:server-port 80
      :cookie-name "slp-session"
      :datomic {:db-uri "datomic:free://localhost:4334/slp"
                :test-uri "datomic:mem://slp"}}
     (read-resource (str "config/environments/" environment ".edn")))))

(defn config
  [& keys]
  (get-in conf keys))