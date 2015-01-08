(ns clj-tut.core.mysql
  (:require [clojure.java.jdbc :as j]))

;connection
(def mysql-db {:subprotocol "mysql"
               :subname "//127.0.0.1:3306/test"
               :user "uroot"})
;schema
(def schema
  [[:users
    [:id :integer "PRIMARY KEY" "AUTO_INCREMENT"]
    [:name "varchar(255)"]
    [:age :integer]]])

(defn create-schema! [schema]
  (doseq [table schema]
    (let [sql (apply j/create-table-ddl table)]
      (j/execute! mysql-db [sql]))))


;CRUD ops
(defn create-user! [user]
  (j/insert! mysql-db :users user))

(defn update-user! [name user]
  (j/update! mysql-db :users user ["name = ?" name]))

(defn fetch-users []
  (j/query mysql-db  ["SELECT * FROM users"]))

(defn find-user-by-name [name]
  (j/query mysql-db  ["SELECT * FROM users WHERE name = ?" name]))

