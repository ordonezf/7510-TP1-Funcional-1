(ns rule
  (:require [clojure.string :as str]
            [utils :as u]
            [fact :as f]))


(defrecord Rule [name args vfact])

(defn parse-rule-facts
  "Parses the facts portion of a rule."
  [fact-str]
  (let [fact-vector (filter #(not= % "") (map str/trim (str/split (str/replace fact-str #"\), " ")\n") #"\n")))]
    fact-vector)
  )

(defn new-rule
  "Creates a rule with format: {:name 'name' :args [args] :vfact [facts]}"
  [rule]
  (let [rule-vector (u/regex-match u/rule-regex rule)]
    (if (= (count rule-vector) 4)
      (new Rule (nth rule-vector 1)
           (str/split (nth rule-vector 2) #", ")
           (map f/new-fact-for-rule (parse-rule-facts (nth rule-vector 3))))
      nil)
  ))