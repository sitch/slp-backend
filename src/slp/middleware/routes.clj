(ns slp.middleware.routes
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [cemerick.friend :as friend]
            [ring.util.response :as resp]
            [slp.controllers.user :as user]
            [slp.controllers.session :as session]
            
;            [slp.controllers.analytics :as analytics]
;            [slp.controllers.accounts :as accounts]
;            [slp.controllers.forms :as forms]
;            [slp.controllers.js :as js]
;            [slp.controllers.loans :as loans]
;            [slp.controllers.profile :as profile]

            )
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
;  (authroute GET "/scripts/load-session.js" js/load-session)
;
;  ;; Serve up web app
;  (apply compojure.core/routes
;         (map #(compojure.core/routes
;                (compojure.route/files "/" {:root %})
;                (compojure.route/resources "/" {:root %}))
;              (reverse (config :html-paths))))
;  (apply compojure.core/routes
;         (map (fn [response-fn]
;                (GET "/" [] (response-fn "index.html" {:root "slp-frontend"})))
;              [resp/file-response resp/resource-response]))
  
  ;; Forms
;  (authroute GET  "/form/:user" forms/show)
;  (authroute GET  "/form/schema" forms/show-schema)
;  (route     GET "/api/form/schema" forms/show-schema)
;  (route     GET "/api/form/status" forms/show-progress)
  
  ;; Accounts
;  (authroute GET  "/accounts" accounts/show)
;  (authroute POST "/accounts" accounts/create!)
;  (authroute GET  "/accounts/:id" accounts/show)
;  (authroute PUT  "/accounts/:id" accounts/update!)
;  (authroute GET  "/accounts/aggregate" accounts/aggregate)

  ;; Loans
;  (authroute GET "/loans/:id" loans/show)
;  (authroute PUT "/loans/:id" loans/update!)
  
  ;; Profile
;  (authroute POST "/profile" profile/create!)
;  (authroute PUT  "/profile/:id" profile/update!)

  ;; User
  (authroute POST "/api/userz" user/registration-success-response)
  (route     GET  "/api/user/:id" user/show)
  (authroute PUT  "/api/user/:id" user/update!)
  (authroute POST "/api/user/:id/password" user/change-password!)

  ;; Analytics
;  (authroute POST "/analytics" analytics/create!)
  
  ;; Auth
  (route POST "/api/login" session/create!)
  (friend/logout
   (ANY "/api/logout" []
        (ring.util.response/redirect "/")))

  (ANY "/api/debug" {:keys [x] :as r}
       (str x))
  
;  (route GET "/" (ring.util.response/redirect "index.html"))
  
  (compojure.route/not-found "Sorry, nothing here."))
