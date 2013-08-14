



sudo apt-get update

sudo apt-get install openjdk-7-jre

sudo apt-get install wget

sudo apt-get install unzip
sudo apt-get install git

sudo wget http://downloads.datomic.com/0.8.4122/datomic-free-0.8.4122.zip
sudo unzip datomic-free-0.8.4122.zip
sudo rm datomic-free-0.8.4122.zip
cd /opt
mkdir local
sudo mv ~/datomic-free-0.8.4122 /opt/local/datomic


cd ~
git clone https://github.com/Sitch/slp-backend.git

cd /opt/local/datomic

bin/transactor ~/slp-backend/resources/transactor.properties

cd ~/slp-backend

#migrate
java -jar slp-0.0.1.jar db/reload

# run
java -jar slp-0.0.1.jar server