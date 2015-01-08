(ns clj-tut.core.middleware
  (:require
    [ring.util.response :as ring])
  (:import (com.fasterxml.jackson.core JsonParseException)))

(def error-codes
  {:invalid 400
   :not-found 404})

(defn wrap-error-handling [handler]
  (fn [req]
    (try
      (or (handler req)
          (ring/not-found "resource not found"))
      (catch JsonParseException e
        {:status  400
         :body    "malformed json"}
        )
      (catch Exception e
        {:status  500
         :body    (.getMessage e)}
        ))))