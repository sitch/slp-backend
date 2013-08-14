(ns slp.ops.config
  (:use environ.core))

(def conf {:aws-access-key-id "AKIAJSS2XC5M2F7T37RQ"
           :aws-secret-key-id "+fEnXVTH68qs84UspCqv2r02R9drTnLrmJRRHH6o"
           :warfile-path "target/slp-0.0.1-SNAPSHOT-standalone.war"}

(defn config
  [& keys]
  (get-in conf keys))