(ns slp.pallet.groups
  (:require
    [pallet.api :as api]
    [pallet.actions :as actions]
    [pallet.configure :as configure]
    [pallet.crate.automated-admin-user :as admin-user]
    [pallet.crate.java :as java]
    [pallet.crate.datomic :as datomic]
    [pallet.crate.upstart :as upstart]))

;
;  (:require pallet.core)
;  (:use slp.ops.pallet.crates
;        slp.ops.pallet.nodes)


;; Nodes
(def ec2-t1-micro
  (api/node-spec
   :image {:os-family :ubuntu :image-id "us-east-1/ami-9c78c0f5"}
    ;; Using a small since datomic defaults to using a 1GB ram
   :hardware {:min-cores 1 :hardware-id "m1.small" }))

;; Actions
(def base-server
  (api/server-spec
   :phases
   {:bootstrap (api/plan-fn (admin-user/automated-admin-user))}))

(def update-server
  (api/server-spec
    :phases
    {:configure (api/plan-fn (actions/package-manager :update))}))

;; Group
(def web-group
  (api/group-spec "webservice"
	  :extends [bootstrap-server
              update-server
              (java/server-spec {:version [7]})
              (datomic/datomic {:version "0.8.4122"})
              (upstart/server-spec {})]
	  :count 1
    :node-spec webservice-ec2-micro))

;
; (group-spec
;   "web-service"
;   webserver
;   :extends [java-server]))

;; Webservice crate
