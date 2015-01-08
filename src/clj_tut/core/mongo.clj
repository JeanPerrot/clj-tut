(ns clj-tut.core.mongo
  (:require [monger.core :as mg]
            [monger.collection :as mc]))

(defn start! []
  (let [conn (mg/connect)]
    (def db (mg/get-db conn "play"))))

(defn- without-mongo-id [body]
  (dissoc body :_id))

(defn create-user!
  "creates a user"
  [user]
  (mc/insert db "users" user ))

(defn update-user!
  "updates a user"
  [name user]
  (mc/update db "users" {:name name} user))

(defn find-user-by-name [name]
  (without-mongo-id
    (mc/find-one-as-map db "users" {:name name})))

(defn fetch-users []
  (map without-mongo-id
       (mc/find-maps db "users")))

