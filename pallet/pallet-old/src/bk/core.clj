;;; Pallet project configuration file
(require '[pallet.crate.java :as java]
         '[pallet.crate.runit :as runit]
         '[pallet.crate.app-deploy :as app-deploy])

(def creds
  {:aws {:provider "aws-ec2",
       :identity "your aws identity",
       :credential "the longer aws credential string"}})

(def ec2-micro
 (node-spec
   :hardware {:hardware-id "t1.micro"
              :min-cores 1 :min-ram 256}
   :image    {:os-family :amzn-linux
							:location-id "us-west-2"
							:image-id "us-west-2/ami-2a31bf1a"}
   :network  {:inbound-ports [22 80]}))

(def webserver
  (group-spec "webserver"
    :extends [(java/server-spec {})
              (datomic/server-spec {:version "0.8.4122"})
              (app-deploy/server-spec
               {:artifacts
                {:from-lein
                 [{:project-path "target/webapp-%s-standalone.war"
                   :path "webapp.war"}]}
                :run-command "java -jar /opt/slp/webapp.war"}
               :instance-id :webapp)]))

(defproject webapp
  :provider {:aws-ec2 {:node-spec ec2-micro}}
  :groups [webserver])




;;; Pallet project configuration file

;;; By default, the pallet.api and pallet.crate namespaces are already referred.
;;; The pallet.crate.automated-admin-user/automated-admin-user us also referred.

;;; (require '[your-ns :refer [your-group]])

(require 'pallet.compute)

(defproject slp
  :provider {:vmfest
             {:node-spec
              {:image {:os-family :ubuntu :os-version-matches "12.04"
                       :os-64-bit true}}
              :selectors #{:default}}})

;lein pallet add-service aws aws-ec2 "AKIAJSS2XC5M2F7T37RQ" "+fEnXVTH68qs84UspCqv2r02R9drTnLrmJRRHH6o"

(def aws 
  {:aws {:provider "aws-ec2" 
         :identity "AKIAJSS2XC5M2F7T37RQ" 
         :credential "+fEnXVTH68qs84UspCqv2r02R9drTnLrmJRRHH6o"}})

(def service
  (pallet.compute/instantiate-provider
   "aws-ec2"
   :identity "AKIAJSS2XC5M2F7T37RQ"
   :credential "+fEnXVTH68qs84UspCqv2r02R9drTnLrmJRRHH6o"))


;; node spec

(def warfile-path (str "target/slp-" (config :version) "-SNAPSHOT-standalone.war")

(def webservice-ec2-micro
 (node-spec
   :image    {:os-family :amzn-linux
							:location-id "us-west-2"
							:image-id "us-west-2/ami-2a31bf1a"}
   
;   :image    {:image-id "us-west-2/ami-2a31bf1a"
;              :location {:location-id "us-west-2"}} 
   :hardware {:hardware-id "t1.micro"
              :min-cores 1 :min-ram 256}

;   :network {:inbound-ports [22 80]}

;	 :phases   {:bootstrap (pallet.resource/phase (admin/automated-admin-user))
;              :configure
;			          (plan-fn
;			             (package-manager :update)
;			             (package "tomcat7")
;			          (remote-file "/var/lib/tomcat7/webapps/myapp.war"
;			             :local-file "target/myApp.war"
;			             :owner "tomcat7"
;			             :group "tomcat7"
;			             :mode 755))
;              :deploy (pallet.resource/phase 
;                        (webdeploy-crate/tomcat-deploy 
;                          (config :warfile-path)))}))



(def web-group
  (group-spec "ws"
	  :extends [with-datomic with-java]
	  :node-spec webservice-ec2-micro))


(require 'pallet.core 'pallet.compute 'pallet.configure)

;startup
(pallet.core/converge
  (pallet.core/group-spec "ws"
   :count 1
   :node-spec (pallet.core/node-spec
                :image {:os-family :ubuntu :image-id "us-east-1/ami-3c994355"}))
   :compute (pallet.configure/compute-service :aws))

;shutdown
(pallet.core/converge
  (pallet.core/group-spec "ws" 
   :count 0)
   :compute (pallet.configure/compute-service :aws))


;admin
(use '[pallet.crate.automated-admin-user :only [automated-admin-user]])
(pallet.core/converge
  (pallet.core/group-spec "mygroup"
   :count 1
   :node-spec (pallet.core/node-spec :image
               {:os-family :ubuntu 
                :image-id "us-east-1/ami-3c994355"})
   :phases {:bootstrap automated-admin-user})
   :compute (pallet.configure/compute-service :aws))





;(defn scale-cluster [n]
;    (pallet.api/converge
;      (pallet.api/group-spec "mygroup" :count n)
;      :compute (pallet.configure/compute-service "aws")))