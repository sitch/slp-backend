(ns slp.config
  (:use environ.core))

(def conf
  (merge-with
   merge
   {:datomic   {:db-uri       "datomic:free://localhost:4334/slp"
                :test-uri     "datomic:mem://slp"}
    :slp-email {:from-address "notifications@studentlendingproject.com"
                :from-name    "Student Lending Project Notifications"}}
   {:slp-email {:username     (env :slp-email-username)
                :password     (env :slp-email-password)}}))

(defn config
  [& keys]
  (get-in conf keys))