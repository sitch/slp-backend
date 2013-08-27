(ns slp.utils 
  (:require [clojure.java.io :as io]))

(defn now [] (java.util.Date.))

(defn str->int
  [str]
  (if (string? str)
    (read-string (re-find #"^-?\d+$" str))
    str))

(defn remove-nils-from-map
  [record]
  (into {} (remove (comp nil? second) record)))

(defn slurp-resource
  [path]
  (-> path
      io/resource
      slurp))

(defn read-resource
  [path]
  (-> path
      slurp-resource
      read-string))