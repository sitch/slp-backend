(ns slp.config
  (:use environ.core))

(def conf
  (merge-with
   merge
   {:server-port (Integer. (get (System/getenv) "PORT" 8080))
    :html-paths ["public"
                 "public"
                 "public"]
    :datomic   {:db-uri       "datomic:free://localhost:4334/slp"
                :test-uri     "datomic:mem://slp"}
    :slp-email {:from-address "notifications@studentlendingproject.com"
                :from-name    "Student Lending Project Notifications"}}
   
   {:slp-email {:username     (env :slp-email-username)
                :password     (env :slp-email-password)}}))


;(def conf
;  (let [environment (or (env :app-env) "development")]
;    (merge
;     {:html-paths ["public"
;                   "public"
;                   "public"]
;      :datomic {:db-uri "datomic:free://localhost:4334/thrive"
;                :test-uri "datomic:mem://thrive"}
;      :send-email false
;      :email {:host "gmail.com"
;              :from-address "notifications@thrivefinance.com"
;              :from-name "Thrive Finance"
;              :username (env :email-username)
;              :password (env :email-password)}}
;     (read-resource (str "config/environments/" environment ".edn")))))


(defn config
  [& keys]
  (get-in conf keys))