(ns slp.models.permissions
  (:use slp.utils
        slp.config))

(defn current-username [auth]
  (:username auth))

(defn current-user-id [auth]
  (:id auth))

(defn logged-in? [auth]
  auth)

(defn current-user-id?
  [id auth]
  (= id (current-user-id auth)))

(defn not-current-user-id?
  [id auth]
  (not (current-user-id? id auth)))

(defn can-modify-profile? [user auth]
  (or
   (= user (current-username auth))
   (= (:username user) (current-username auth))))

(defn owner?
  [record auth]
  (or
   (= (:author-id record) (:id auth))
   (= (get-in record [:author :id]) (:id auth))
   (and
    (not (nil? (:username auth)))
    (= (get-in record [:author :username]) (:username auth)))))
