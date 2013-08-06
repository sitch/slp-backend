(ns slp.models.mailer
  (:use slp.utils
        slp.paths
        slp.config
        environ.core)
  (:import org.apache.commons.mail.SimpleEmail))

(defn msg 
  "format the message"
  [username post]
  (str "Hi " username ",

You've received a new comment on Grateful Place. You can reply to it by visiting http://slp.com/#/topics/" (:topic_id post) ". Here's the comment:

" (:content post) "


=========
You can change your email preferences by going to http://slp.com/#/profile/email

If you'd like to stop receiving emails but don't want to log in, please email notifications@slp.com, and I apologize for the inconvenience - one-click unsubscribe is on my to-do list.

Take care!
Daniel Higginbotham, maintainer of Grateful Place"))

(defn send-post-notification
  "send an email for a new comment"
  [user post]
  (doto (SimpleEmail.)
    (.setHostName "smtp.gmail.com")
    (.setSslSmtpPort "465")
    (.setSSL true)
    (.addTo (:email user))
    (.setFrom (config :gp-email :from-address) (config :gp-email :from-name))
    (.setSubject "You have a new comment on Grateful Place")
    (.setMsg (msg (:username user) post))
    (.setAuthentication (config :gp-email :username) (config :gp-email :password))
    (.send)))