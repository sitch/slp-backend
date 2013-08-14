(ns slp.pallet.core
  (:require
    [pallet.api :as api]
    [pallet.actions :as actions]
    [pallet.configure :as configure]
    [pallet.crate.app-deploy :as app-deploy]
    [pallet.crate.automated-admin-user :as admin-user]
    [pallet.crate.java :as java]
;    [pallet.crate.jetty :as jetty]
    [pallet.crate.datomic :as datomic]
    [pallet.crate.upstart :as upstart]))

;
;  (:require pallet.core)
;  (:use slp.ops.pallet.crates
;        slp.ops.pallet.nodes)


;(def ec2-m1-small-ubuntu
;  (api/node-spec
;   :image {:os-family :ubuntu :image-id "us-east-1/ami-9c78c0f5"}
;    ;; Using a small since datomic defaults to using a 1GB ram
;   :hardware {:min-cores 1 :hardware-id "m1.small" }))


;; Nodes
(def ec2-t1-micro
  (api/node-spec
   :image {:os-family :amzn-linux 
           :image-id "us-west-2/ami-2a31bf1a"}
   :hardware {:min-cores 1
              :hardware-id "t1.micro"}
   :network {:incoming-ports [22 4500]}))

;; Actions
(def base-server
  (api/server-spec
   :phases
   {:bootstrap (api/plan-fn (admin-user/automated-admin-user))}))

(def update-server
  (api/server-spec
    :phases
    {:configure (api/plan-fn (actions/package-manager :update))}))






(def datomic-migrations
  (app-deploy/server-spec
    {:run-command "java -jar /opt/slp/slp.jar db/migrate"}
    :instance-id :slp))

(def start-jetty
  (app-deploy/server-spec
    {:run-command "java -jar /opt/slp/slp.jar server"}
    :instance-id :slp))

(def deploy-jar
  (app-deploy/server-spec
    {:artifacts
 	    {:from-lein
		    [{:project-path "target/webapp-%s-standalone.jar"
		      :path "slp.jar"}]}}
    :instance-id :slp))





;; Group
(def webservice-spec
  (api/group-spec "webservice"
	  :extends [bootstrap-server
              update-server
              (java/server-spec {:version [7]})
              (datomic/datomic {:version "0.8.4122"})
              deploy-jar
              datomic-migrations
              start-jetty
              
              ]
;              (upstart/server-spec {})]
	  :count 1
    :node-spec ec2-t1-micro))


;(defn init
;  []
;  (api/converge
;    webservice-spec
;    :phase :install
;    :compute (configure/compute-service :aws)))

;(defn update
;  []
;  (api/lift
;    datomic-box-spec
;    :phase :install
;    :compute (configure/compute-service :aws)))



;
; (group-spec
;   "web-service"
;   webserver
;   :extends [java-server]))

;; Webservice crate
