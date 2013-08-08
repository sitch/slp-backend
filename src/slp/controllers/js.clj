(ns slp.controllers.js
  (:require [cemerick.friend :as friend]))

(defn load-session
  [params auth]
  (let [session-js
        (fn [value]
          {:body (str "window.slp={}; window.slp.session = " value ")")
           :headers {"content-type" "application/javascript"}})]
    (if auth
      (session-js (str "{username:'" (:username auth) "', id: " (:id auth) "}"))
      (session-js "null"))))
