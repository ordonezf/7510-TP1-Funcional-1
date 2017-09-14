(ns query-runner
  (:require [clojure.string :as str]
            [utils :as u]
            [rule :as r]
            [fact :as f]))

(defn is-query-true?
  "Returns true if the query follows the rule, else false"
  [query rule db]
  (let [rule-args (zipmap (:args rule) (:args query))
        facts (:vfact rule)
        rule-facts-names (map #(:name %) facts)
        rule-facts-arg-names (map #(:args %) facts)
        args-translated (map #(u/argument-dictionary rule-args %) rule-facts-arg-names)]
        (= (count (filter #(u/is-fact-true? % (:facts db))
                          (map f/simple-new-fact rule-facts-names args-translated)))
           (count facts))
    )
  )

(defn evaluate-rule
  "Returns true if the rule exists and is correct, otherwise false."
  [query db]
  (let [rule (filter #(= (. % name) (. query name)) (:rules db))]
  (if (= (count rule) 1)
    (if (u/query-has-correct-args? query (nth rule 0))
      (is-query-true? query (nth rule 0) db)
      false)
    false)
  )
  )


(defn run-query
  "Runs the query and returns True or False"
  [query db]
  (let [fact (f/new-fact query)]
  (cond
    ;(= (count (filter #(= % fact) (:facts db))) 1) true
    (u/is-fact-true? fact (:facts db)) true
    (evaluate-rule fact db) true
    :else false
    )
  ))
