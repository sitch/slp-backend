(ns slp.db.test
  (:require [datomic.api :as d]
            [slp.db.query :as q]
            [slp.db.manage :as manage])
  (:use slp.config)
  (:import java.io.File))

(def test-db-uri (config :datomic :test-uri))
(def initialized (atom false))

(defmacro with-test-db
  [& body]
  `(binding [q/*db-uri* test-db-uri]
     (initialize)
     ~@body))

(defn initialize
  []
  (doall (manage/reload)))
