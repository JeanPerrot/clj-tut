(ns clj-tut.core.hello-world-test
  (:require [clojure.test :refer :all]
            [clj-tut.core.hello-world :refer :all]))

(defn request [method uri]
  {:server-port 80,
   :server-name "localhost",
   :remote-addr "localhost",
   :uri uri,
   :query-string nil,
   :scheme :http,
   :request-method method,
   :headers {"host" "localhost"}}
  )

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World!"))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
