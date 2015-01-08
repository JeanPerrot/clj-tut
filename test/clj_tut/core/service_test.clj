(ns clj-tut.core.service-test
  (:require [clojure.test :refer :all]
            [clj-tut.core.service :refer :all]))

(deftest palindrome
  (is (is-palindromic? "bob"))
  (is (is-palindromic? "Bob"))
  (is (not (is-palindromic? "albert"))))

