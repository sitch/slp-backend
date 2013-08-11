(ns slp.ws.utils)

(defn remove-nils-from-map
  [record]
  (into {} (remove (comp nil? second) record)))

(defn str->int
  [str]
  (if (string? str)
    (read-string (re-find #"^-?\d+$" str))
    str))

(defn now
  []
  (java.util.Date.))
