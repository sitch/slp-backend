(ns slp.ops.pallet.crates
  (:use (clojure.contrib strint core))
  (:require (pallet core resource)
					  pallet.resource.service
					  (pallet.crate
				      [tomcat :as tomcat]
				      [etc-default :as default]) 
            [pallet.crate.java :as java]
            [pallet.crate.lein :as lein]
            [pallet.crate.datomic :as datomic]))

(def with-java
  (java/server-spec {}))

(def with-lein
  (lein/leiningen {}))

(def with-datomic
  (datomic/server-spec 
	 {:version "0.8.4122"
		:type "free"
		:user "datomic"
		:supervisor :upstart
		:service-name "datomic"
		:verify false ;; Don't verify the conf script
		:group "datomic"
		:config-path "/etc/datomic"
		:config {:protocol "free" :host "localhost" :port "4334"
		         :data-dir "/var/lib/datomic/data"
		         :log-dir "/var/log/datomic"}}))

(defn tomcat-deploy
  "Deploys the specified .war file to tomcat.  An optional :port kwarg
   defines the port that tomcat will serve on (defaults to 80)."
  [pallet-request warfile & {:keys [port] :or {port 80}}]
  (pallet.resource.service/with-restart pallet-request "tomcat*"
    (default/write "tomcat7"
      ; configure tomcat's heap to utilize 2/3 of machine's total memory
      :JAVA_OPTS (->> pallet-request
                   :target-node
                   .getHardware
                   .getRam
                   (* 0.66)
                   int
                   (format "-Xmx%sm"))
      ; allow tomcat to run on ports < 1024
      :AUTHBIND "yes")
    (tomcat/server-configuration
      (tomcat/server
        (tomcat/service
          (tomcat/connector :port (str port) :protocol "HTTP/1.1"
            :connectionTimeout "20000"
            :redirectPort "8443"))))
    (tomcat/deploy "ROOT" :local-file warfile :clear-existing true)))