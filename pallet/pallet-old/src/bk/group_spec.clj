(ns slp.ops.group-spec)

(def aws-access-key-id "AKIAJSS2XC5M2F7T37RQ")
(def aws-secret-key-id "+fEnXVTH68qs84UspCqv2r02R9drTnLrmJRRHH6o")




(def java-server 
  (java/server-spec {
    :vendor :oracle 
    :components #{:jdk} 
    :version [7]}))

(def webserver
 (node-spec
   :image    {:os-family :ubuntu} 
   :hardware {:min-cores 1 :min-ram (* 2 1024)}
   :phases {:configure
            (plan-fn
               (package-manager :update)
               (package "tomcat7")
               (remote-file "/var/lib/tomcat7/webapps/myapp.war"
               :local-file "target/myApp.war"
               :owner "tomcat7"
               :group "tomcat7"
               :mode 755))

(def web-group
 (group-spec
   "my-webservers"
   webserver
   :extends [java-server]))