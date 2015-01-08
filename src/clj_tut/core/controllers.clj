(ns clj-tut.core.controllers
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.server.standalone :refer [serve]]
            [ring.util.response :as ring]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [clj-tut.core.mongo :as mongo]
            ))



(defroutes api-routes
  (GET "/user/:name" [name]
       (ring/response
         (mongo/find-user-by-name name)))
  (PUT "/user/:name" {body :body {name :name} :params}
       (mongo/update-user name body)
       (ring/response
         "success"))
  (POST "/users" {body :body}
        (mongo/create-user body)
        (ring/response "created!")))


(def app
  (-> api-routes
      (wrap-json-response)
      (wrap-defaults api-defaults)
      (wrap-json-body {:keywords? true})
      ))

(defn start []
  (def server (serve app {:port 8080})))

(defn stop []
  (.stop server))

(defn restart []
  (stop)
  (start))
