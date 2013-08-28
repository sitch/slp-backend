(ns slp.db.maprules
  (:require [slp.db.tools.query :as db]
            [slp.utils :as utils]
            cemerick.friend.credentials)
  (:use [flyingmachine.cartographer.core]))

(defn ref-count
  [ref-attr]
  #(ffirst (db/q [:find '(count ?c) :where ['?c ref-attr (:db/id %)]])))

(def date-format (java.text.SimpleDateFormat. "yyyy-MM-dd HH:mm:ss Z"))

(defn format-date
  [date]
  (.format date-format date))

(def dbid #(or (utils/str->int (:id %)) #db/id[:db.part/user]))

(defmaprules ent->profile
  (attr :id :db/id)
  (attr :firstname :user.profile/firstname)
  (attr :lastname :user.profile/lastname))

(defmaprules ent->user
  (attr :id :db/id)
  (attr :username :user/username)
  (attr :email :user/email))

(defmaprules ent->userauth
  (attr :id :db/id)
  (attr :password :user/password)
  (attr :username :user/username)
  (attr :email :user/email))

(defmaprules user->txdata
  (attr :db/id dbid)
  (attr :user/username :username)
  (attr :user/email :email)
  (attr :user/password #(cemerick.friend.credentials/hash-bcrypt (:password %))))

(defmaprules change-password->txdata
  (attr :db/id #(utils/str->int (:id %)))
  (attr :user/password #(cemerick.friend.credentials/hash-bcrypt (:new-password %))))

(defmaprules profile->txdata
  (attr :user/firstname :firstname)
  (attr :user/lastname :lastname))