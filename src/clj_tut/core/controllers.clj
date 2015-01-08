(ns clj-tut.core.controllers
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [ring.server.standalone :refer [serve]]
            [ring.util.response :as ring]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [clj-tut.core.middleware :as m]
            [clj-tut.core.service :as service]
            [clj-tut.core.mongo :as mongo]))


(defroutes api-routes
  (GET "/user/:name" [name]
       (ring/response
         (service/fetch-user name)))
  (PUT "/user/:name" {body :body {name :name} :params}
       (service/update-user! name body)
       (ring/response "success"))
  (GET "/users" []
       (ring/response
         (service/fetch-users)))
  (POST "/users" {body :body}
        (service/create-user! body)
        (ring/response "created!"))
  )


(def app
  (-> api-routes
      (wrap-json-body {:keywords? true})
      (wrap-json-response)
      (wrap-defaults api-defaults)
      (m/wrap-error-handling)
      ))

(defn start []
  (mongo/start)
  (def server (serve app {:port 8080})))

(defn stop []
  (.stop server))

(defn restart []
  (stop)
  (start))
