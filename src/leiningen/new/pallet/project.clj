(defproject {{name}} "{{project-version}}"
  :description "FIXME Pallet project for {{name}}"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [{{pallet-groupid}}/pallet "{{pallet-version}}"]
                 {{#with-pallet-jclouds}}
                 [{{pallet-jclouds-groupid}}/pallet-jclouds "{{pallet-jclouds-version}}"]
                 ;; To get started we include all jclouds compute providers.
                 ;; You may wish to replace this with the specific jclouds
                 ;; providers you use, to reduce dependency sizes.
                 [{{jclouds-groupid}}/jclouds-allblobstore "{{jclouds-version}}"]
                 [{{jclouds-groupid}}/jclouds-allcompute "{{jclouds-version}}"]
                 [{{jclouds-groupid}}.driver/jclouds-slf4j "{{jclouds-version}}"
                  ;; the declared version is old and can overrule the
                  ;; resolved version
                  :exclusions [org.slf4j/slf4j-api]]
                 [{{jclouds-groupid}}.driver/jclouds-sshj "{{jclouds-version}}"]
                 {{/with-pallet-jclouds}}
                 {{#with-pallet-vmfest}}
                 [{{pallet-vmfest-groupid}}/pallet-vmfest "{{pallet-vmfest-version}}"]
                 {{/with-pallet-vmfest}}
                 [ch.qos.logback/logback-classic "1.0.9"]]
  :profiles {:dev
             {:dependencies
              [[{{pallet-groupid}}/pallet "{{pallet-version}}"
                :classifier "tests"]]
              :plugins
              [[{{pallet-lein-groupid}}/pallet-lein "{{pallet-lein-version}}"]]}
             :leiningen/reply
             {:dependencies [[org.slf4j/jcl-over-slf4j "1.7.2"]]
              :exclusions [commons-logging]}}
  :local-repo-classpath true
  :repositories
  {"sonatype" "https://oss.sonatype.org/content/repositories/releases/"})
