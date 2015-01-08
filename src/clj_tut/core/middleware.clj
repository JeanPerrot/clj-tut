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
        {}
        (ring/response {"error" "malformed json"} 400))
      (catch Exception e
        (json-response {"error" "malformed json"} 400)
        (let [{:keys [type message]} (meta e)]
          (json-response {"error" message} (error-codes type)))))))