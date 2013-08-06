(ns slp.paths
  (use flyingmachine.webutils.paths))

(create-path-fns "user" :username "password")
(create-path-fns "form" :id)
(create-path-fns "profile" :id)