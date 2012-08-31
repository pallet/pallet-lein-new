(defproject {{name}} "{{project-version}}"
  :description "FIXME Pallet project for {{name}}"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.cloudhoist/pallet "{{pallet-version}}"]
                 {{#with-pallet-jclouds}}
                 [org.cloudhoist/pallet-jclouds "{{pallet-jclouds-version}}"]
                 ;; To get started we include all jclouds compute providers.
                 ;; You may wish to replace this with the specific jclouds
                 ;; providers you use, to reduce dependency sizes.
                 [org.jclouds/jclouds-allblobstore "{{jclouds-version}}"]
                 [org.jclouds/jclouds-allcompute "{{jclouds-version}}"]
                 [org.jclouds.driver/jclouds-slf4j "{{jclouds-version}}"
                  ;; the declared version is old and can overrule the
                  ;; resolved version
                  :exclusions [org.slf4j/slf4j-api]]
                 [org.jclouds.driver/jclouds-sshj "{{jclouds-version}}"]
                 {{/with-pallet-jclouds}}
                 {{#with-pallet-vmfest}}
                 [org.cloudhoist/pallet-vmfest "{{pallet-vmfest-version}}"]
                 {{/with-pallet-vmfest}}
                 [ch.qos.logback/logback-classic "1.0.0"]]
  :dev-dependencies [[org.cloudhoist/pallet
                      "{{pallet-version}}" :type "test-jar"]
                     {{#with-growl}}
                     [org.cloudhoist/pallet-growl "{{pallet-growl-version}}"]
                     {{/with-growl}}
                     [org.cloudhoist/pallet-lein "0.5.2"]]
  :profiles {:dev
             {:dependencies
              [[org.cloudhoist/pallet "{{pallet-version}}" :classifier "tests"]]
              :plugins [[org.cloudhoist/pallet-lein "0.5.2"]]}}
  :local-repo-classpath true
  :repositories
  {"sonatype-snapshots" "https://oss.sonatype.org/content/repositories/snapshots"
   "sonatype" "https://oss.sonatype.org/content/repositories/releases/"}
  :repl-options {:init (do (require '{{name}}.repl)
                           ({{name}}.repl/force-slf4j))})
