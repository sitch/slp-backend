(defproject slp "0.0.1-SNAPSHOT"
  :description "Student Lending Project"
  :url "http://www.studentlendingproject.com"
  :repositories [["central-proxy" "http://repository.sonatype.org/content/repositories/central/"]]

;  :omit-source true
;  :jvm-opts ["-Xmx2G"]
  :resource-paths ["resources"]
  
  :pedantic? :abort ;abort on dependency version inconsistencies
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.clojure/tools.namespace "0.2.4"]
                 [org.clojure/data.json "0.2.2"]
                 
                 ;; Database
                 [com.datomic/datomic-free "0.8.4122"]
                 
                 ;; Web Services
                 [ring/ring-core "1.2.0"]
                 [ring/ring-jetty-adapter "1.2.0"]
                 [ring-middleware-format "0.3.0"]
                 [compojure "1.1.5"]
                 [liberator "0.9.0"]
                 [environ "0.4.0"] ;environment
                 [crypto-random "1.1.0"] ;crypto
                 [com.cemerick/friend "0.1.5"] ;auth
                 [org.webbitserver/webbit "0.4.15"] ;web sockets
                 
;                 [prismatic/plumbing "0.1.0"] ;prismatic
;                 [readyforzero/babbage "1.0.2"] ;compute engine
                 
                 [com.flyingmachine/webutils "0.1.1"] ;paths
                 [flyingmachine/cartographer "0.1.1"] ;mapping
                 
                 ;; Infrastructure
                 [org.clojars.strad/datomic-crate "0.8.8"]
                 [com.palletops/app-deploy-crate "0.8.0-alpha.2"]
                 [com.palletops/lein-crate "0.8.0-alpha.1"]
                 [com.palletops/java-crate "0.8.0-beta.5"]]

  :plugins [[lein-ancient "0.4.4"]
            [lein-environ "0.4.0"]
            [lein-ring "0.8.6"]
            [com.palletops/pallet-lein "0.8.0-alpha.1"]]

  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]
                                  [ring/ring-devel "1.2.0"]
                                  [midje "1.5.1"]]}}
  
  ;; Ring
  :ring {:handler slp.ws.server/app}
  
  ;; Pallet
;  :pallet {:source-paths ["src-pallet"] :resource-paths []}
;  :pallet {:dependencies [[org.virtualbox/vboxjws "4.2.6"]]}
  
  :main slp.ws.app)