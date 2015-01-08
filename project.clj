(defproject clj-tut "0.1.0-SNAPSHOT"
  :description "having fun here"
  :url "http://treepeople.pdx"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [ring-server "0.3.1"]
                 [ring/ring-json "0.3.1"]
                 [halresource "0.2.0-SNAPSHOT"]
                 [com.novemberain/monger "2.0.1"]
                 [com.googlecode.libphonenumber/libphonenumber "7.0.1"]
                 ]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler clj-tut.core.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})


