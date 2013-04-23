(ns leiningen.new.pallet
  "Pallet project template for use with lein newnew"
  (:use
   leiningen.new.templates
   [leiningen.core.main :only [debug]]))

(def render (renderer "pallet"))

(def default-features
  {:with-pallet true
   :pallet-version "0.7.4"
   :project-version "0.1.0-SNAPSHOT"
   :vmfest-version nil
   :jclouds-version nil
   :with-pallet-vmfest nil
   :pallet-vmfest-version "0.2.4"
   :with-pallet-jclouds true
   :pallet-jclouds-version "1.5.2"
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

(defn pallet->group-id
  [pallet-version]
  (cond
    (re-find #"0.8." pallet-version) "com.palletops"
    :else "org.cloudhoist"))

(defn with-groupid-for-pallet
  [data]
  (let [p (:pallet-version data)
        g (:pallet-groupid data)]
    (if (and p (not g))
      (assoc data :pallet-groupid (pallet->group-id p))
      data)))

(defn pallet->pallet-lein-version
  [pallet-version]
  (cond
    (re-find #"0.8." pallet-version) "0.6.0-beta.9"
    :else "0.5.2"))

(defn with-pallet-lein-version-for-pallet
  [data]
  (let [p (:pallet-version data)
        l (:pallet-lein-version data)]
    (if (and p (not l))
      (assoc data :pallet-lein-version (pallet->pallet-lein-version p))
      data)))

(defn pallet-jclouds->jclouds
  [pallet-jclouds-version]
  (cond
    (re-find #"1.5." pallet-jclouds-version) "1.5.5"
    (re-find #"1.4." pallet-jclouds-version) "1.4.2"
    (re-find #"1.3.0-alpha" pallet-jclouds-version) "1.3.2"
    (re-find #"1.3." pallet-jclouds-version) "1.3.2"
    (re-find #"1.2." pallet-jclouds-version) "1.2.1"
    :else "1.3.1"))

(defn with-jclouds-version-for-pallet-jclouds
  [data]
  (let [p-j (:pallet-jclouds-version data)
        j (:jclouds-version data)]
    (if (and p-j (not j))
      (assoc data :jclouds-version (pallet-jclouds->jclouds p-j))
      data)))

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
   with-groupid-for-pallet
   with-pallet-lein-version-for-pallet
   with-jclouds-version-for-pallet-jclouds
   with-automated-admin-user-dependency))

;;; main template entry point
(defn pallet
  "A Pallet project template.
The template requires a project name, and an optional list of
feature/value pairs.  Recognised features with example values are:

  with-pallet 0.8.0-beta.9
  with-pallet-jclouds 1.5.2
  with-pallet-vmfest true
  with-vmfest 0.3.0-alpha.3
  with-jclouds 1.5.5"
  [proj-name & features]
  (let [data (merge
              default-features
              (feature-map features)
              {:name proj-name
               :sanitized (sanitize proj-name)})
        data (infer-features data)]
    (debug "data is" (pr-str data))
    (->files
     data
     ["src/{{sanitized}}/groups/{{sanitized}}.clj" (render "group.clj" data)]
     ["resources/logback.xml" (render "logback.xml" data)]
     ["project.clj" (render "project.clj" data)]
     ["README.md" (render "README.md" data)])))
