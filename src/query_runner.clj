(ns query-runner
  (:require [clojure.string :as str]
            [utils :as u]
            [rule :as r]
            [fact :as f]))

(defn query-has-correct-args?
  "Returns true if the query has valid arguments"
  [query rule]
  (= (count (. query args)) (count (. rule args)))
  )

(defn fun
  [dic fact]
  (map #(get dic %) fact)
  )

(defn fun2
  [fact fact-db]
  (= (count (filter #(= % fact) fact-db)) 1))

(defrecord Factn [name args])

(defn is-query-true?
  "Returns true if all the facts from the query/rule are true, else false"
  [query rule db]
  (let [args (zipmap (. rule args) (. query args))
        facts (. rule vfact)
        names (map #(. % name) facts)
        args-translated (map #(fun args %) facts)]
        ;(= (count (filter #(fun2 % (:facts db)) (map ->Factn names args-translated))) (count facts))
        (println (filter #(fun2 % (:facts db)) (map ->Factn names args-translated)))
    )
  )

(defn evaluate-rule
  "Returns true if the rule exists and is correct, otherwise false."
  [query db]
  (let [rule (filter #(= (. % name) (. query name)) (:rules db))]
  (if (= (count rule) 1)
    (if (query-has-correct-args? query (nth rule 0))
      (is-query-true? query (nth rule 0) db)
      false)
    false)
  )
  )


(defn run-query
  "Runs the query and returns True or False"
  [db query]
  (let [fact (f/new-fact query)]
  (cond
    (= (count (filter #(= % fact) (:facts db))) 1) true
    (evaluate-rule fact db) true
    :else false
    )
  ))
