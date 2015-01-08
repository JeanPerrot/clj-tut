(ns clj-tut.core.service
  (:require [clj-tut.core.mysql :as mysql]))

(defn fetch-user [name]
  (mysql/find-user-by-name name))

(defn fetch-users []
  (take 100 (mysql/fetch-users)))

(defn update-user! [name body]
  (mysql/update-user! name body))

(defn create-user! [ body]
  (mysql/create-user! body))

(defn reverse-string [s]
  (apply str (reverse s)))

(defn is-palindromic? [name]
  (let [lc (.toLowerCase name)]
    (= lc (reverse-string lc))))

(defn fetch-palindromic-names []
  (filter (comp is-palindromic? :name) (mysql/fetch-users)))

