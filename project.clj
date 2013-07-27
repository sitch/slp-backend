(defproject clojure-rest "0.1.0-SNAPSHOT"
      :description "REST Serviec"
      :url "http://www.studentlendingproject.com"
      :dependencies [[org.clojure/clojure "1.5.1"]
                     [compojure "1.1.5"]
                     [ring/ring-json "0.2.0"]
                     [c3p0/c3p0 "0.9.1.2"]
                     [org.clojure/java.jdbc "0.3.0-alpha4"]
                     [com.h2database/h2 "1.3.168"]
                     [cheshire "5.2.0"]
                     [com.datomic/datomic-free "0.8.4020.26"]]
      :plugins [[lein-ring "0.8.6"]]
      :ring {:handler clojure-rest.handler/app}
      :profiles
      {:dev {:dependencies [[ring-mock "0.1.5"]]}})