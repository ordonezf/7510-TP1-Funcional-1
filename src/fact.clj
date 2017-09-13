(ns fact
  (:require [clojure.string :as str]
            [utils :as u]))


(defrecord Fact [name args])

(defn new-fact
  "Receives a string and returns a fact if valid or nil if invalid."
  [fact]
  (let [fact-vector (u/regex-match u/fact-regex fact)]
  (if (= (count fact-vector) 3)
    (new Fact (nth fact-vector 1) (str/split (nth fact-vector 2) #", "))
    nil)
  )
  )

(defn simple-new-fact
  "Simple fact constructor."
  [name args]
  (new Fact name args)
  )

(defn new-fact-for-rule
  "Creates a special fact just for a rule."
  [fact]
  (let [fact-vector (u/regex-match u/fact-in-rule-regex fact)]
    (if (= (count fact-vector) 3)
      (new Fact (nth fact-vector 1) (str/split (nth fact-vector 2) #", "))
      nil)
    )
  )