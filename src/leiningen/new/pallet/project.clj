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
                 [org.jclouds.driver/jclouds-slf4j "{{jclouds-version}}"]
                 {{/with-pallet-jclouds}}
                 {{#with-pallet-vmfest}}
                 [org.cloudhoist/pallet-vmfest "{{pallet-vmfest-version}}"]
                 {{/with-pallet-vmfest}}
                 [org.slf4j/slf4j-api "1.6.1"]
                 [ch.qos.logback/logback-core "1.0.0"]
                 [ch.qos.logback/logback-classic "1.0.0"]]
  :dev-dependencies [[org.cloudhoist/pallet "{{pallet-version}}" :type "test-jar"]
                     {{#with-growl}}
                     [org.cloudhoist/pallet-growl "{{pallet-growl-version}}"]
                     {{/with-growl}}
                     [org.cloudhoist/pallet-lein "0.5.0"]]
  :profiles {:dev {:dependencies [[org.cloudhoist/pallet-lein "0.5.0"]]}}
  :local-repo-classpath true
  :repositories
  {"sonatype-snapshots" "https://oss.sonatype.org/content/repositories/snapshots"
   "sonatype" "https://oss.sonatype.org/content/repositories/releases/"})
