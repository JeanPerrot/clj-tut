(ns clj-tut.core.populate
  (:require [clj-tut.core.mysql :as mysql]))


(def csv-first-names "http://www.quietaffiliate.com/Files/CSV_Database_of_First_Names.csv")

(defn first-names []
  (->> (slurp "http://www.quietaffiliate.com/Files/CSV_Database_of_First_Names.csv")
       (#(.split % "\r"))
       (drop 1)))

(defn random-dudes []
  (for [name (first-names)]
    { :name name
     :age (int (rand 100))}))

(defn save-many
  [users]
  (doseq [user users] (mysql/create-user! user)))

(defn populate-db []
  (save-many (random-dudes)))


