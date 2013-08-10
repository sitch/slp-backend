# Student Lending Project

## Usage

```
lein uberjar
java -jar target/studentlendingproject-0.1.0-SNAPSHOT-standalone.jar server
```


## Deploy
```
<!-- export RING_ENV=production -->
lein ring uberwar
```



# AWS SETUP

##Credentials
```
:access-key 
:secret-key

```


```
<!-- Java Home Path -->
export JAVA_HOME=/path/to/jdk

<!-- Java Executables Path -->
export PATH=$JAVA_HOME/bin:$PATH

<!-- ************* -->
<!-- DATOMIC SETUP -->
<!-- ************* -->
bin/transactor conf/free-aws-transactor.properties

java -jar target/studentlendingproject-0.0.1-standalone.jar db/reload



```

# PALLET

From: http://stackoverflow.com/questions/17028427/how-to-deploy-a-clojure-web-application-to-amazon-ec2-aws-elastic-beanstalk-l

##Group spec
```
(def java-server 
  (java/server-spec {
    :vendor :oracle 
    :components #{:jdk} 
    :version [7]}))
```

##Node spec
```
(def webserver
 (node-spec
   :image {:os-family :ubuntu} 
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
   "my-websertvers"
   webserver
   :extends [java-server]))
```
