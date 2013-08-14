(ns slp.pallet.crates
  (:use (clojure.contrib strint core))
  (:require (pallet core compute configure resource)
					  pallet.resource.service
					  (pallet.crate
				      [tomcat :as tomcat]
				      [etc-default :as default]) 
            [pallet.crate.java :as java]
            [pallet.crate.lein :as lein]
            [pallet.crate.datomic :as datomic])
  (:use [pallet.crate.automated-admin-user :only [automated-admin-user]]))

(def nodespec
  (pallet.core/node-spec
   :image {:os-family :ubuntu :image-id "us-east-1/ami-3c994355"}
   :compute (pallet.configure/compute-service :aws)))

;start
(pallet.core/converge
  (pallet.core/group-spec "mygroup"
   :count 1
   :node-spec (pallet.core/node-spec
               :image {:os-family :ubuntu :image-id "us-east-1/ami-3c994355"}))
               :compute (pallet.configure/compute-service :aws))

;shutdown
(pallet.core/converge
  (pallet.core/group-spec "mygroup" :count 0)
  :compute (pallet.configure/compute-service :aws))

;auth
(pallet.core/converge
  (pallet.core/group-spec "mygroup"
   :count 1
   :node-spec (pallet.core/node-spec
               :image {:os-family :ubuntu :image-id "us-east-1/ami-3c994355"})
   :phases {:bootstrap automated-admin-user})
  :compute (pallet.configure/compute-service :aws))











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
