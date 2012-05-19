(defproject pallet/lein-template "0.2.0"
  :description "Leiningen plugin for creating Pallet projects"
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.2.1"]]}
             :1.3 {:dependencies [[org.clojure/clojure "1.3.0"]]}
             :1.4 {:dependencies [[org.clojure/clojure "1.4.0-beta2"]]}}
  :checksum-deps true
  :eval-in-leiningen true)
