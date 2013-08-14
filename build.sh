

cd ../slp-frontend
grunt build
cp public/js/build.js ../slp-backend/resources/public/js/build.js
cp public/js/templates.js ../slp-backend/resources/public/js/templates.js

cd ../slp-backend

lein uberjar
java -jar target/slp-0.0.1-SNAPSHOT-standalone.jar server