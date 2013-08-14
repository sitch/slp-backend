(ns slp.utils)

(defn now [] (java.util.Date.))

(defn str->int
  [str]
  (if (string? str)
    (read-string (re-find #"^-?\d+$" str))
    str))

(defn remove-nils-from-map
  [record]
  (into {} (remove (comp nil? second) record)))
