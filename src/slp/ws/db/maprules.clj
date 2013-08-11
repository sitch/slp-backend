(ns slp.ws.db.maprules
  (:require [slp.ws.db.query :as db]
            cemerick.friend.credentials)
  (:use [flyingmachine.cartographer.core]
        [slp.ws.utils]
        [clavatar.core]))

(defn ref-count
  [ref-attr]
  #(ffirst (db/q [:find '(count ?c) :where ['?c ref-attr (:db/id %)]])))

(def date-format (java.text.SimpleDateFormat. "yyyy-MM-dd HH:mm:ss Z"))

(defn format-date
  [date]
  (.format date-format date))

(defn sorted-content
  [content-attribute sort-fn]
  #(sort-by sort-fn (slp.db.query/all content-attribute [:content/author (:db/id %)])))

(def dbid #(or (str->int (:id %)) #db/id[:db.part/user]))

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
  (attr :db/id #(str->int (:id %)))
  (attr :user/password #(cemerick.friend.credentials/hash-bcrypt (:new-password %))))

