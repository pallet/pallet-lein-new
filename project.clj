(defproject pallet/lein-template "0.2.8"
  :description "Leiningen plugin for creating Pallet projects"
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.2.1"]]}
             :1.3 {:dependencies [[org.clojure/clojure "1.3.0"]]}
             :1.4 {:dependencies [[org.clojure/clojure "1.4.0"]]}
             :1.5 {:dependencies [[org.clojure/clojure "1.5.0-RC16"]]}}
  :checksum-deps true
  :eval-in-leiningen true
  :url "https://github.com/pallet/pallet-lein-new"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"})
