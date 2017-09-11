(ns rule
  (:require [clojure.string :as str]
            [utils :as u]
            [fact :as f]))


(defrecord Rule [name args vfact])

(defn facts-for-rule
  ""
  [fact-str]
  (let [fact-vector (filter #(not= % "") (map str/trim (str/split (str/replace fact-str #"\), " ").\n") #"\n")))]
    fact-vector)
  )

(defn new-rule
  [rule]
  (let [rule-vector (rule-regex rule)]
    (if (= (count rule-vector) 4)
      (new Rule (nth rule-vector 1)
                (str/split (nth rule-vector 2) #", ")
                (map new-fact-for-rule (facts-for-rule (nth rule-vector 3))))
      nil)
  ))

(defn fact-regex
  "Returns the fact if it's valid, otherwise nil"
  [fact]
  (re-matches #"^([a-z]+)\(([[a-z]+[, [a-z]+]?]+)\)\.$" fact)
  )


(defn rule-regex
  "Returns the rule if it's valid, otherwise nil"
  [rule]
  (re-matches #"^([a-z]+)\(([[A-Z]+, ]+)\) :- ([[a-z]+\(([[A-Z]+, ]+)\)]+)\.$" rule)
  )