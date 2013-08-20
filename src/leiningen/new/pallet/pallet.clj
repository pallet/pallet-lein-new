;;; Pallet project configuration file

(require
 '[{{name}}.groups.{{name}} :refer [{{name}}]])

(defproject {{name}}
  :provider {:jclouds
             {:node-spec
              {:image {:os-family :ubuntu :os-version-matches "12.04"
                       :os-64-bit true}}}}

  :groups [{{name}}])
