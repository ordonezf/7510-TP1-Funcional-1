(ns query-runner
  (:require [clojure.string :as str]
            [utils :as u]
            [rule :as r]
            [fact :as f]))



(defn evaluate-rule
  "Returns true if the rule exists and is correct, otherwise false."
  [query db]
  (let [rule (r/new-rule query)])
  )

(defn run-query
  "Runs the query and returns True or False"
  [db query]
  (cond
    (not= (filter #(= % (f/new-fact query)) (:facts db)) []) true
    (evaluate-rule query db) true
    :else false
    )
  )