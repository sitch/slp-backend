(defproject studentlendingproject "0.1.0-SNAPSHOT"
  :description "Student Lending Project"
  :url "http://www.studentlendingproject.com"
  :repositories [["central-proxy" "http://repository.sonatype.org/content/repositories/central/"]]
  :resource-paths ["lib/*"]
  :jvm-opts ["-Xmx2G"]

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [com.datomic/datomic-free "0.8.4020.26"]
                 [environ "0.4.0"]
                 [ring "1.2.0"]
                 [ring-mock "0.1.5"]
                 [ring-middleware-format "0.3.0"]
                 [compojure "1.1.5"]
                 [liberator "0.9.0"]
                 [com.cemerick/friend "0.1.5"]
                 [crypto-random "1.1.0"]
                 [org.clojure/tools.namespace "0.2.2"]
                 [com.flyingmachine/webutils "0.1.1"]
                 [flyingmachine/cartographer "0.1.1"]
                 [markdown-clj "0.9.25"]
                 [clavatar "0.2.1"]
                 [org.apache.commons/commons-email "1.2"]
                 [org.clojure/data.json "0.2.2"]]

  :plugins [[lein-environ "0.4.0"]
            [lein-beanstalk "0.2.7"]]

  :resource-paths ["resources"]
  
  :profiles {:dev {:dependencies [[midje "1.5.0"]]}}

  :main slp.app)