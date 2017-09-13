(ns database-maker
  (:require [clojure.string :as str]
            [utils :as u]
            [rule :as r]
            [fact :as f]))

(defn create-database
  "Returns a database in a dictionary {:facts ({facts}) :rules ({rules})}."
  [db]
  (let [[& db-vector] (filter #(not= % "") (map str/trim (str/split db #"\n|\.")))]
    (let [rules (filter #(u/is-rule? %) db-vector)
          facts (filter #(u/is-fact? %) db-vector)]
      {:rules (map r/new-rule rules) :facts (map f/new-fact facts)})
    )
  )