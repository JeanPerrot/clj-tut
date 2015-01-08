(ns clj-tut.core.service
  (:require [clj-tut.core.mongo :as mongo]))

(defn fetch-user [name]
  (mongo/find-user-by-name name))

(defn fetch-users []
  (take 100 (mongo/fetch-users)))

(defn update-user! [name body]
  (mongo/update-user! name body))

(defn create-user! [ body]
  (mongo/create-user! body))


