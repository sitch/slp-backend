(ns slp.ops.main
  (:require [org.clojars.strad/datomic-crate :as datomic-crate]))

(def datomic-server
 (group-spec "my-node-with-datomic"
   :extends [(pallet.crate.datomic/server-spec {:version 0.8.4122})]
   :node-spec small-node))



(converge {datomic-server 1}
          :phase [:configure :start]
;          :compute aws-beanstalk
          :compute aws-ec2)