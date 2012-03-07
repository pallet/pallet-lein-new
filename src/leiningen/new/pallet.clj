(ns leiningen.new.pallet
  "Pallet project template for use with lein newnew"
  (:use leiningen.new.templates))

(def render (renderer "pallet"))

(def default-features
  {:with-pallet true
   :pallet-version "0.7.0-beta.1"
   :project-version "0.1.0-SNAPSHOT"
   :vmfest-version nil
   :jclouds-version nil
   :with-pallet-vmfest nil
   :pallet-vmfest-version "0.2.0-beta.1"
   :with-pallet-jclouds true
   :pallet-jclouds-version "1.3.0-alpha.1"
   :with-growl nil
   :pallet-growl-version "0.1.0-SNAPSHOT"})

(defn is-version-spec?
  [v]
  (and v (string? v) (re-matches #"[0-9]+\..*" v)))

;; The features supplied as arguments to the template should be name value
;; pairs. These are converted into a map, and the first of each pair is made
;; a keyword.
(defn feature-map
  [features]
  (into {}
        (map
         #(vector (keyword (first %)) (second %))
         (partition 2 features))))

;;; Once all features are specified, we infer some values for the templates.
(defn with-automated-admin-user-dependency
  [data]
  (if (nil? (:with-automated-admin-user-dependency data))
    (if (re-matches #"0.[4-6]\..*" (:pallet-version data))
      (assoc data :with-automated-admin-user-dependency true)
      data)
    data))

(defn pallet-jclouds->jclouds
  [pallet-jclouds-version]
  (cond
    (re-find #"1.4." pallet-jclouds-version) "1.4.0-rc.1"
    (re-find #"1.3." pallet-jclouds-version) "1.3.1"
    (re-find #"1.2." pallet-jclouds-version) "1.2.1"
    :else "1.3.1"))

(defn with-jclouds-version-for-pallet-jclouds
  [data]
  (let [p-j (:pallet-jclouds-version data)
        j (:jclouds-version data)]
    (if (and p-j (not j))
      (assoc data :jclouds-version (pallet-jclouds->jclouds p-j)))))

(defn with-feature-or-version
  [data with-key version-key]
  (if (is-version-spec? (get data with-key))
    (assoc data version-key (get data with-key))
    data))

(defn infer-features
  [data]
  (->
   data
   (with-feature-or-version :with-pallet :pallet-version)
   (with-feature-or-version :with-vmfest :vmfest-version)
   (with-feature-or-version :with-growl :pallet-growl-version)
   (with-feature-or-version :with-jclouds :jclouds-version)
   (with-feature-or-version :with-pallet-jclouds :pallet-jclouds-version)
   (with-feature-or-version :with-pallet-vmfest :pallet-vmfest-version)
   with-jclouds-version-for-pallet-jclouds
   with-automated-admin-user-dependency))

;;; main template entry point
(defn pallet
  "A Pallet project template. The template requires a project name, and an
   optional list of feature/value pairs."
  [proj-name & features]
  (let [data (merge
              default-features
              (feature-map features)
              {:name proj-name
               :sanitized (sanitize proj-name)})
        data (infer-features data)]
    (->files
     data
     ["src/{{sanitized}}/groups/{{sanitized}}.clj" (render "group.clj" data)]
     ["resources/logback.xml" (render "logback.xml" data)]
     ["project.clj" (render "project.clj" data)])))
