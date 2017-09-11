(ns query-runner
  (:require [clojure.string :as str]
            [utils :as u]
            [rule :as r]
            [fact :as f]))



(defn evaluate-rule
  "Returns true if the rule exists and is correct, otherwise false."
  [fact db]
  (if (not= (filter #(= % (. fact name)) (map :name (:rules db))) [])
    true
    false
  ))
  

(defn run-query
  "Runs the query and returns True or False"
  [db query]
  (let [fact (f/new-fact query)]
  (cond
    (not= (filter #(= % fact) (:facts db)) []) true
    (evaluate-rule fact db) 5
    :else false
    )
  ))
