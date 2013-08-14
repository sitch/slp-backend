(ns datomic-box.core
  (:require
    [pallet.api :as api]
    [pallet.actions :as actions]
    
;    [pallet.configure :as configure]
;    [pallet.crate.app-deploy :as app-deploy]
;    [pallet.crate.runit :as runit]
;    [pallet.crate.automated-admin-user :as admin-user]
    [pallet.crate.java :as java]
;    [pallet.crate.git :as git]
;    [pallet.crate.datomic :as datomic]
;    [pallet.crate.upstart :as upstart]
;    [pallet.stevedore :as stevedore]
    
))


;(defpallet
;  :services
;    {:aws
;      {:provider "ec2" :identity "key" :credential "secret-key"
;       :environment
;         {:user {:username "admin"
;                 :private-key-path "/path/to/private-key"
;                 :public-key-path "/path/to/public-key"}}}})


;(def ec2-m1-small-ubuntu
;  (api/node-spec
;   :image {:os-family :ubuntu :image-id "us-east-1/ami-9c78c0f5"}
;    ;; Using a small since datomic defaults to using a 1GB ram
;   :hardware {:min-cores 1 :hardware-id "m1.small" }))



(def west-micro
  (api/node-spec
   :image {:os-family :ubuntu
           :os-version-matches "12.04"
           :os-64-bit true
           :image-id "us-west-2/ami-c6cc43f6"}
   :hardware {:min-cores 1
              :hardware-id "t1.micro"}))


;; Nodes
(def ec2-t1-micro
  (api/node-spec
   :image {:os-family :ubuntu
           :os-version-matches "12.04"
           :os-64-bit true
           :image-id "us-east-1/ami-e2861d8b"}
   :hardware {:min-cores 1
              :hardware-id "m1.small"}
;              :hardware-id "t1.micro"}
;   :network {:incoming-ports [22 4500]}
   ))

;; Actions
;(def bootstrap-server
;  (api/server-spec
;   :phases
;   {:bootstrap (api/plan-fn (admin-user/automated-admin-user))}))

;(def update-server
;  (api/server-spec
;    :phases
;    {:configure (api/plan-fn (actions/package-manager :update))}))

;
;(def datomic-migrations
;  (app-deploy/server-spec
;    {:run-command "java -jar /opt/slp/slp.jar db/migrate"}
;    :instance-id :my-datomic-box))
;
;(def start-jetty
;  (app-deploy/server-spec
;    {:run-command "java -jar /opt/slp/slp.jar server"}
;    :instance-id :my-datomic-box))
;
;(def deploy-jar
;  (app-deploy/server-spec
;    {:artifacts
; 	    {:from-lein
;		    [{:project-path "target/slp-0.0.1-SNAPSHOT-standalone"
;		      :path "slp.jar"}]}
;     :run-command "java -jar /opt/slp/slp.jar db/migrate; java -jar /opt/slp/slp.jar server"
;      
;      }
;    :instance-id :my-datomic-box))
;
;

;
;:install
;admin-user/automated-admin-user
;package-manager update ( unzip )
; get java-jre openjdk 7
;apt-get leininegen


;; Group
(def datomic-box-spec
  (api/group-spec "datomic-box"
	  :extends ['(println "start")
;             bootstrap-server
;              update-server
              (java/server-spec {})
;              (git/server-spec {})

;               (datomic/datomic {:version "0.8.4122"})
;              (datomic/server-spec {:version "0.8.4122"})

;               update-server

;              (runit/server-spec {})
;              deploy-jar
;              datomic-migrations
;              start-jetty
;              (upstart/server-spec {})
              '(println "end")]
	  :count 1
   :node-spec west-micro))
;    :node-spec ec2-t1-micro))

