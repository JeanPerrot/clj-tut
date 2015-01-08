(ns clj-tut.core.hello-world
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.server.standalone :refer [serve]]
            ))

(defn say-hi [name]
  (str "hi " name))

(defroutes app-routes
           (GET "/" [] "Hello World!")
           (GET "/hi/:name" [name] (say-hi name))
           (route/not-found "not found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn start []
  (def server (serve app {:port 8080})))

(defn stop []
  (.stop server))

(defn restart []
  (stop)
  (start))
