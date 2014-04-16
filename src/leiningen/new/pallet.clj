(ns leiningen.new.pallet
  "Pallet project template for use with lein newnew"
  (:use
   leiningen.new.templates
   [leiningen.core.main :only [debug]]))

(def render (renderer "pallet"))

(def default-features
  {:with-pallet true
   :pallet-version "0.8.0-RC.9"
   :project-version "0.1.0-SNAPSHOT"
   :vmfest-version nil
   :jclouds-version nil
   :with-pallet-vmfest nil
   :pallet-vmfest-version "0.4.0-alpha.1"
   :with-pallet-jclouds true
   :pallet-jclouds-version "1.7.1"
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

(defn pallet->pallet-lein-version
  [version]
  (cond
    (re-find #"^0\.[1-7]\." version) "1.5.2"
    :else "0.8.0-alpha.1"))

(defn pallet->group-id
  [version]
  (cond
    (re-find #"^0\.[1-7]\." version) "org.cloudhoist"
    :else "com.palletops"))

(defn pallet-lein->group-id
  [version]
  (cond
    (re-find #"^0\.[1-7]\." version) "org.cloudhoist"
    :else "com.palletops"))

(defn pallet-jclouds->group-id
  [version]
  (cond
    (re-find #"^1\.[1-4]\." version) "org.cloudhoist"
    (re-find #"^1\.5\.[0-2]$" version) "org.cloudhoist"
    :else "com.palletops"))

(defn pallet-vmfest->group-id
  [version]
  (cond
    (re-find #"^0\.[1-2]\." version) "org.cloudhoist"
    :else "com.palletops"))

(defn with-groupid-for-pallet
  [data]
  (let [p (:pallet-version data)
        g (:pallet-groupid data)]
    (if (and p (not g))
      (assoc data :pallet-groupid (pallet->group-id p))
      data)))

(defn with-groupid-for-pallet-lein
  [data]
  (let [p (:pallet-lein-version data)
        g (:pallet-lein-groupid data)]
    (if (and p (not g))
      (assoc data :pallet-lein-groupid (pallet-lein->group-id p))
      data)))

(defn with-groupid-for-pallet-jclouds
  [data]
  (let [p (:pallet-jclouds-version data)
        g (:pallet-jclouds-groupid data)]
    (if (and p (not g))
      (assoc data :pallet-jclouds-groupid (pallet-jclouds->group-id p))
      data)))

(defn with-groupid-for-pallet-vmfest
  [data]
  (let [p (:pallet-vmfest-version data)
        g (:pallet-vmfest-groupid data)]
    (if (and p (not g))
      (assoc data :pallet-vmfest-groupid (pallet-vmfest->group-id p))
      data)))

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
    (re-find #"1.7." pallet-jclouds-version) "1.7.1"
    (re-find #"1.5." pallet-jclouds-version) "1.5.5"
    (re-find #"1.4." pallet-jclouds-version) "1.4.2"
    (re-find #"1.3.0-alpha" pallet-jclouds-version) "1.3.2"
    (re-find #"1.3." pallet-jclouds-version) "1.3.2"
    (re-find #"1.2." pallet-jclouds-version) "1.2.1"
    :else "1.3.1"))

(defn groupid-for-jclouds-version [jclouds-version]
  (if (re-find #"1.7." jclouds-version)
    "org.apache.jclouds"
    "org.jclouds"))

(defn with-jclouds-version-for-pallet-jclouds
  [data]
  (let [p-j (:pallet-jclouds-version data)
        j (:jclouds-version data)
        inferred-jclouds-version (pallet-jclouds->jclouds p-j)]
    (if (and p-j (not j))
      (-> data (assoc :jclouds-version inferred-jclouds-version)
          (assoc :jclouds-groupid (groupid-for-jclouds-version
                                   inferred-jclouds-version)))
      (assoc data :jclouds-groupid (groupid-for-jclouds-version j)))))

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
   with-pallet-lein-version-for-pallet
   with-groupid-for-pallet
   with-groupid-for-pallet-lein
   with-groupid-for-pallet-jclouds
   with-groupid-for-pallet-vmfest
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
     ["pallet.clj" (render "pallet.clj" data)]
     ["README.md" (render "README.md" data)])))
