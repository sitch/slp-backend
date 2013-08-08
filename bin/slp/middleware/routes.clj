(ns slp.middleware.routes
  (:require compojure.route
            compojure.handler
            [ring.util.response :as resp]
            [slp.controllers.js :as js]
;            [slp.controllers.analytics :as analytics]
            [slp.controllers.forms :as forms]
;            [slp.controllers.profile :as profile]
            [slp.controllers.session :as session]
            [slp.controllers.users :as users]
            [cemerick.friend :as friend])
  (:use [compojure.core :as compojure.core :only (GET PUT POST DELETE ANY defroutes)]
        slp.config))

(defmacro route
  [method path handler]
  `(~method ~path {params# :params}
            (~handler params#)))

(defmacro authroute
  [method path handler]
  (let [params (quote params)]
    `(~method ~path {:keys [~params] :as req#}
              (~handler ~params (friend/current-authentication req#)))))

(defroutes routes
  (authroute GET "/scripts/load-session.js" js/load-session)

  ;; Serve up web app
  (apply compojure.core/routes
         (map #(compojure.core/routes
                (compojure.route/files "/" {:root %})
                (compojure.route/resources "/" {:root %}))
              (reverse (config :html-paths))))
  (apply compojure.core/routes
         (map (fn [response-fn]
                (GET "/" [] (response-fn "index.html" {:root "slp-frontend"})))
              [resp/file-response resp/resource-response]))
  
  ;; Forms
;  (authroute GET  "/form/:user" forms/show)
;  (authroute GET  "/form/schema" forms/show-schema)
  (route     GET "/form/schema" forms/show-schema)
  (route     GET "/form/status"   forms/show-progress)

  ;; Profile
;  (authroute POST "/profile" profile/create!)
;  (authroute PUT  "/profile/:id" profile/update!)

  ;; Users
  (authroute POST "/users" users/registration-success-response)
  (route     GET  "/users/:id" users/show)
  (authroute POST "/users/:id" users/update!)
  (authroute POST "/users/:id/password" users/change-password!)

  ;; Analytics
;  (authroute POST "/analytics/:id" analytics/create!)
  
  ;; Auth
  (route POST "/login" session/create!)
  (friend/logout
   (ANY "/logout" []
        (ring.util.response/redirect "/")))

  (ANY "/debug" {:keys [x] :as r}
       (str x))
  
  (compojure.route/not-found "Sorry, there's nothing here."))
