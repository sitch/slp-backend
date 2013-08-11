(ns slp.ws.db.transactions
  (:require [datomic.api :as d]
            [slp.ws.db.maprules :as mr]
            [slp.ws.db.query :as db]
;            [slp.models.mailer :as mailer]
            [flyingmachine.cartographer.core :as c])
  (:use slp.ws.utils))

;(defn create-post
;  [params]
;  (let [{:keys [topic-id author-id]} params
;        post-tempid (d/tempid :db.part/user -1)
;        now (now)
;        post (remove-nils-from-map {:post/content (:content params)
;                                    :post/topic topic-id
;                                    :post/created-at now
;                                    :content/author author-id
;                                    :db/id post-tempid})
;        watches (db/all :watch/topic [:watch/topic topic-id])
;        result (db/t [post
;                      {:db/id topic-id :topic/last-posted-to-at now}
;                      [:increment-watch-count topic-id author-id]])]
;
;    ;; TODO find a better home for this
;    (future
;      (doseq [watch watches]
;        (let [user (c/mapify (:watch/user watch) mr/ent->user)]
;          (if (and
;               (:receive-watch-notifications user)
;               (not= author-id (:id user)))
;            (mailer/send-post-notification user params)))))
;    
;    {:result result
;     :tempid post-tempid}))
;
;(defn update-post
;  [params]
;  (fn [_] (db/t [(c/mapify params mr/post->txdata)])))
;
;(defn create-topic
;  [params]
;  (let [topic-tempid (d/tempid :db.part/user -1)
;        post-tempid (d/tempid :db.part/user -2)
;        watch-tempid (d/tempid :db.part/user -3)
;        author-id (:author-id params)
;        topic (remove-nils-from-map {:topic/title (:title params)
;                                     :topic/first-post post-tempid
;                                     :topic/last-posted-to-at (now)
;                                     :content/author author-id
;                                     :content/deleted false
;                                     :db/id topic-tempid})
;        watch {:db/id watch-tempid
;               :watch/unread-count 0
;               :watch/topic topic-tempid
;               :watch/user author-id}
;        post {:post/content (:content params)
;              :post/topic topic-tempid
;              :post/created-at (now)
;              :content/author author-id
;              :db/id post-tempid}]
;
;    {:result (db/t [topic post watch])
;     :tempid topic-tempid}))
;
;(defn create-watch
;  [params]
;  (let [watch-tempid (d/tempid :db.part/user -1)]
;    {:result (db/t [{:db/id watch-tempid
;                     :watch/unread-count 0
;                     :watch/topic (:topic-id params)
;                     :watch/user (:user-id params)}])
;     :tempid watch-tempid}))

(defn create-user
  [params]
  (println "USER PARAMS" params)
  (let [params (remove-nils-from-map (c/mapify params mr/user->txdata))]
    {:result (db/t [params])
     :tempid (:db/id params)}))

;(defn reset-watch-count
;  [topic user]
;  (let [watch (db/one [:watch/topic topic] [:watch/user user])]
;    (db/t [{:db/id (:db/id watch) :watch/unread-count 0}])))