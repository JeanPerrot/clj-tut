(ns clj-tut.core.mongo
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  )

(defn start []
  (let [conn (mg/connect)]
    (def db (mg/get-db conn "play"))))

(defn- without-mongo-id [body]
  (dissoc body :_id))


(defn create-user
  "creates a user"
  [user]
  (mc/insert db "users" user ))

(defn update-user
  "updates a user"
  [name user]
  (mc/update db "users" {:name name} user))


(defn find-user-by-name [name]
  (without-mongo-id
    (mc/find-one-as-map db "users" {:name name})))


(defn save-many
  [users]
  (doseq [user users] (create-user user)))


;-------------------------------
(def csv-first-names "http://www.quietaffiliate.com/Files/CSV_Database_of_First_Names.csv")

(defn first-names []
  (->> (slurp "http://www.quietaffiliate.com/Files/CSV_Database_of_First_Names.csv")
      (#(.split % "\r"))
      (drop 1)))

(defn random-dudes []
  (for [name (first-names)]
    { :name name
     :age (int (rand 100))}
    ))

(defn avg [numbers]
  (/ (reduce + numbers) (count numbers)))

(defn users-average-age []
  (let [users (into [] (mc/find db "users"))
        ages (map #(get % "age") users)]
    (avg (remove nil? ages)))
  )

(def state (atom nil))