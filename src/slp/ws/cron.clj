(ns slp.ws.cron)

(defn backup-name
  []
  (str "backup-" (timestamp)))

(defn db-backup
  []
  (db/backup (env :resources (backup-name)))