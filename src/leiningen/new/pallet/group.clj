(ns {{name}}.groups.{{name}}
    "Node defintions for {{name}}"
    (:require
     [pallet.core :refer [group-spec server-spec node-spec]]
     [pallet.crate.automated-admin-user :refer [automated-admin-user]]
     [pallet.phase :refer [phase-fn]]))

(def default-node-spec
  (node-spec
   :image {:os-family :ubuntu}
   :hardware {:min-cores 1}))

(def
  ^{:doc "Defines the type of node {{name}} will run on"}
  base-server
  (server-spec
   :phases
   {:bootstrap (phase-fn (automated-admin-user))}))

(def
  ^{:doc "Define a server spec for {{name}}"}
  {{name}}-server
  (server-spec
   :phases
   {:configure (phase-fn
                 ;; Add your crate class here
                 )}))

(def
  ^{:doc "Defines a group spec that can be passed to converge or lift."}
  {{name}}
  (group-spec
   "{{name}}"
   :extends [base-server {{name}}-server]
   :node-spec default-node-spec))
