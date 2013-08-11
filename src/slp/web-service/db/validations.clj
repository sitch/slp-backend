(ns slp.ws.db.validations
  (:require [slp.ws.db.query :as db])
  (:import org.mindrot.jbcrypt.BCrypt))

(def user-validations
  {:username
   ["Your username must be between 4 and 24 characters long"
    #(and
      (not-empty %)
      (>= (count %) 4)
      (<= (count %) 24))
    "That username is already taken"
    #(or
      (empty? %)
      (not (db/one [:user/username %])))]
   
   :password
   ["Your password must be at least 4 characters long"
    #(and
      (not-empty %)
      (>= (count %) 4))]
   
   :email
   ["You must enter a valid email address"
    #(and
      (not-empty %)
      (re-find #"@" %))

    "That email address is already taken"
    #(or
      (empty? %)
      (not (db/one [:user/email %])))]})

(def user
  {:create (select-keys user-validations [:username :password :email])
   :update (select-keys user-validations [:email])})

(defn email-update
  [existing-user]
  (let [email-check (into [] (take 3 (:email user-validations)))]
    {:email
     (conj email-check
           (fn [email]
             (or
              (empty? email)
              (if-let [user (db/one [:user/email email])]
                (= (:id existing-user) (:db/id user))
                true))))}))

(def change-password
  {:new-password
   ["Your passwords do not match"
    #(= (:new-password %) (:new-password-confirmation %))

    "Your password must be at least 4 characters long"
    #(>= (count (:new-password %)) 4)]
   
   :current-password
   ["You didn't enter the correct value for your current password"
    #(BCrypt/checkpw (:current-password %) (:password %))]})

(def post-validations
  {:content
   ["Your post can't be blank."
    #(not-empty %)]

   :topic-id
   ["Your post must have a topic"
    #(not (nil? %))]})

(def post
  {:create post-validations
   :update (select-keys post-validations [:content])})

(def topic
  {:content
   ["Your post can't be blank."
    #(not-empty %)]})