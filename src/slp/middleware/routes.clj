(ns slp.middleware.routes
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [cemerick.friend :as friend]
            [ring.util.response :as resp]
            [slp.controllers.user :as user]
            [slp.controllers.profile :as profile]
            [slp.controllers.session :as session])
  (:use [compojure.core :as compojure.core :only (GET PUT POST DELETE ANY defroutes)]))

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
  ;; User
  (authroute POST "/api/user" user/registration-success-response)
  (route     GET  "/api/user/:id" user/show)
;  (authroute PUT  "/api/user/:id" user/update!)
  (authroute POST "/api/user/:id/password" user/change-password!)
  
  ;; Profile
  (authroute GET  "/api/profile/:id"     profile/show)
;  (authroute PUT  "/api/profile/:id" profile/update!)

  ;; Auth
  (route POST "/api/login" session/create!)
  (friend/logout
   (ANY "/api/logout" []
        (ring.util.response/redirect "/")))

  (ANY "/api/debug" {:keys [x] :as r}
       (str x))
  
  (compojure.route/not-found "Sorry, nothing here."))
