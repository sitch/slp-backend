(ns slp.config
  (:use environ.core))

(def conf
  (merge-with
   merge
   {:server-port 8080
    :html-paths ["public"
                 "public"
                 "public"]
    :datomic   {:db-uri       "datomic:free://localhost:4334/slp"
                :test-uri     "datomic:mem://slp"}
    :slp-email {:from-address "notifications@studentlendingproject.com"
                :from-name    "Student Lending Project Notifications"}}
   
   {:slp-email {:username     (env :slp-email-username)
                :password     (env :slp-email-password)}}))

(defn config
  [& keys]
  (get-in conf keys))