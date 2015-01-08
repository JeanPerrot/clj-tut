(ns clj-tut.core.populate
  (:use clj-tut.core.mongo)
  (:require [monger.collection :as mc]))


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

(defn save-many
  [users]
  (doseq [user users] (create-user! user)))

(defn populate-randomly []
  (save-many (random-dudes)))


(defn avg [numbers]
  (/ (reduce + numbers) (count numbers)))

(defn users-average-age []
  (let [users (into [] (mc/find db "users"))
        ages (map #(get % "age") users)]
    (avg (remove nil? ages)))
  )

