(ns slp.ops.core
  (:require pallet.core)
  (:use slp.ops.config))

(defonce service
  (delay
    (let [access-key (config :aws-access-key-id)
          secret-key (config :aws-secret-key-id)]
      (assert (and access-key secret-key))
      (jcompute/compute-service "ec2" access-key secret-key :ssh :log4j))))

(defn deploy
  [prefix]
  (when-not webdeploy-nodes/warfile-path
    (throw (IllegalStateException. "No .war file defined, cannot deploy")))
  (pallet.core/converge {webdeploy-nodes/appserver 1}
    :compute @service :phase [:deploy] :prefix prefix))

(defn shutdown
  [prefix]
  (pallet.core/converge {webdeploy-nodes/appserver 0}
    :compute @service :prefix prefix))

 