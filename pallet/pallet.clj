;; Pallet
(require '[datomic-box.core :refer [datomic-box-spec]])

(defproject datomic-box
  :groups [datomic-box-spec])


:pallet {
                      :dependencies [
                      
                        
				                         
                 [com.palletops/pallet "0.8.0-RC.1"]
                 
                 ;crates
;                  [com.palletops/all-crates "0.8.0-alpha.2"]
;                 [org.clojars.strad/datomic-crate "0.8.8"]
                 
                 

                  [com.palletops/java-crate "0.8.0-beta.5"]
;                 [com.palletops/runit-crate "0.8.0-alpha.1"]
;                 [com.palletops/app-deploy-crate "0.1.0"]

                  
                  
                   ; jclouds
                   [com.palletops/pallet-jclouds "1.5.3"]
;                   [org.cloudhoist/pallet-jclouds "1.5.2"]
;                   [org.jclouds/jclouds-blobstore "1.5.5"]
;                   [org.jclouds/jclouds-compute "1.5.5"]

                   [org.jclouds.provider/aws-ec2 "1.5.5"]
;                   [org.jclouds.provider/aws-s3 "1.5.5"]
                   
                   
;                   [org.jclouds/jclouds-all "1.5.5"]
;                   [org.jclouds/jclouds-allblobstore "1.5.5"]
;                   [org.jclouds/jclouds-allcompute "1.5.5"]
                       
;                       [org.jclouds.driver/jclouds-slf4j "1.5.5"]
;                       [org.jclouds.driver/jclouds-sshj "1.5.5"]
;                       
;                       
;                       [ch.qos.logback/logback-classic "1.0.9"]
;                       [org.slf4j/jcl-over-slf4j "1.7.3"]


;                 [com.palletops/java-crate "0.8.0-beta.5"]
;                 [com.palletops/upstart-crate "0.8.0-alpha.2"]
;                 [com.palletops/app-deploy-crate "0.8.0-alpha.2"]
;                 [com.palletops/runit-crate "0.8.0-alpha.1"]
;                 [com.palletops/git-crate "0.8.0-alpha.2"]
                 
;                 [com.palletops/stevedore "0.8.0-beta.4"]
;                 [com.palletops/lein-crate "0.8.0-alpha.1"]
;                 [com.palletops/script-exec "0.3.5"]


                 ;; To get started we include all jclouds compute providers.
                 ;; You may wish to replace this with the specific jclouds
                 ;; providers you use, to reduce dependency sizes.
;                 [org.jclouds/jclouds-allblobstore "1.5.5"]
;                 [org.jclouds/jclouds-allcompute "1.5.5"]
                 [org.jclouds.driver/jclouds-slf4j "1.5.5"
                  ;; the declared version is old and can overrule the
                  ;; resolved version
                  :exclusions [org.slf4j/slf4j-api]]
                 [org.jclouds.driver/jclouds-sshj "1.5.5"]
                 [ch.qos.logback/logback-classic "1.0.9"]

                      
                      ]
;                      
                      :source-paths ["pallet/src"]
                      :resource-paths ["resources"]
                      }