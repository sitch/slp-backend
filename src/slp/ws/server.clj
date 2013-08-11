(ns slp.ws.server
  (:require [slp.ws.db.manage :as db])
  (:use clojure.stacktrace
        [ring.adapter.jetty :only (run-jetty)]
        ring.middleware.format
        ring.middleware.params
        ring.middleware.session
        ring.middleware.keyword-params
        ring.middleware.nested-params
        [slp.middleware.auth :only (auth)]
        [slp.middleware.routes :only (routes)]
        [slp.middleware.db-session-store :only (db-session-store)]))

(defn -main
  "Start the jetty server"
  []
  (run-jetty #'app {:port (Integer. (get (System/getenv) "PORT" 8080)) :join? false}))

; The ring app
(def app
  (-> routes
      auth
      debug
      wrap))

(defn debug [f]
  (fn [{:keys [uri request-method params session] :as request}]
    (println (str "--------------------------------------"
                  request-method uri "\n"
                  session "\n"
                  params "\n"))
    (f request)))

(defn wrap
  [to-wrap]
  (-> to-wrap
      (wrap-session {:cookie-name "slp-session" :store (db-session-store {})})
      (wrap-restful-format :formats [:json-kw])
      wrap-exception
;      wrap-hateoas
      wrap-keyword-params
      wrap-nested-params
      wrap-params))

(defn wrap-exception [f]
  (fn [request]
    (try (f request)
      (catch Exception e
        (do
          (.printStackTrace e)
          {:status 500
           :body "Exception caught"})))))

; https://github.com/talios/wellrested
;(defn wrap-hateoas [f]
;  "Provide all necessary HATEOAS compliant links"
;  (fn [request]
;    (f request)))