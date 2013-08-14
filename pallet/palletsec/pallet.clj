;;; Pallet project configuration file
(require '[pallet.crate.java :as java]
         '[pallet.crate.runit :as runit]
         '[pallet.crate.app-deploy :as app-deploy])


(def auth
  (pallet.compute/instantiate-provider
   "aws-ec2"
   :identity "AKIAJSS2XC5M2F7T37RQ"
   :credential "+fEnXVTH68qs84UspCqv2r02R9drTnLrmJRRHH6o"))

(def webservice-ec2-micro
 (node-spec
   :image    {:os-family :amzn-linux
							:location-id "us-west-2"
							:image-id "us-west-2/ami-2a31bf1a"}
   
;   :image    {:image-id "us-west-2/ami-2a31bf1a"
;              :location {:location-id "us-west-2"}} 

   :hardware {:hardware-id "t1.micro"
              :min-cores 1 :min-ram 256}
   
   :network {:inbound-ports [22 80]}
   :phases   {:bootstrap (pallet.resource/phase 
                           (admin/automated-admin-user))
              :configure
;			          (plan-fn
;			             (package-manager :update)
;			             (package "tomcat7")
;			          (remote-file "/var/lib/tomcat7/webapps/myapp.war"
;			             :local-file "target/myApp.war"
;			             :owner "tomcat7"
;			             :group "tomcat7"
;			             :mode 755))
              :deploy (pallet.resource/phase 
                        (webdeploy-crate/tomcat-deploy 
                          (config :warfile-path)))}))
   
