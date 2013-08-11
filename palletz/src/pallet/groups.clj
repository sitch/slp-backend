(ns slp.ops.pallet.groups
  (:require pallet.core)
  (:use slp.ops.pallet.crates
        slp.ops.pallet.nodes)

(def web-group
  (group-spec "webservice"
	  :extends [with-datomic with-java]
	  :node-spec webservice-ec2-micro))

;
; (group-spec
;   "web-service"
;   webserver
;   :extends [java-server]))

;; Webservice crate
