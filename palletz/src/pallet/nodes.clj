(ns slp.ops.pallet.nodes
  (:use slp.ws.config))

;(def java-server 
;  (java/server-spec {:vendor :oracle 
;                     :components #{:jdk} 
;                     :version [7]}))

(def warfile-path (str "target/slp-" (config :version) "-SNAPSHOT-standalone.war")

(def webservice-ec2-micro
 (node-spec
   :image    {:image-id "us-west-2/ami-2a31bf1a"
              :location {:location-id "us-west-2"}} 
   :hardware {:hardware-id "t1.micro"
              :min-cores 1 :min-ram 256}
;   :network {:inbound-ports [22 80]}
	 :phases   {:bootstrap (pallet.resource/phase (admin/automated-admin-user))
              :configure
			          (plan-fn
			             (package-manager :update)
			             (package "tomcat7")
			          (remote-file "/var/lib/tomcat7/webapps/myapp.war"
			             :local-file "target/myApp.war"
			             :owner "tomcat7"
			             :group "tomcat7"
			             :mode 755))
              :deploy (pallet.resource/phase 
                        (webdeploy-crate/tomcat-deploy 
                          (config :warfile-path)))}))