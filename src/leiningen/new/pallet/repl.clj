(ns {{sanitized}}.repl
  (:require
   clojure.tools.logging
   clojure.tools.logging.slf4j))

(defn force-slf4j
  "The repl task brings in commons-logging, which messes up our logging
configuration. This is an attempt to restore sanity."
  []
  (binding [*ns* (the-ns 'clojure.tools.logging.slf4j)]
    (alter-var-root
     #'clojure.tools.logging/*logger-factory*
     (constantly (clojure.tools.logging.slf4j/load-factory)))))
