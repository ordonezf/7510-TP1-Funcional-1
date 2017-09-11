(ns fact
  (:require [clojure.string :as str]
            [utils :as u]))


(defrecord Fact [name args])

(defn new-fact
  [fact]
  (let [fact-vector (u/fact-regex fact)]
  (if (= (count fact-vector) 3)
    (new Fact (nth fact-vector 1) (str/split (nth fact-vector 2) #", "))
    nil)
  )
  )

(defn new-fact-for-rule
  [fact]
  (let [fact-vector (u/fact-regex-rule fact)]
    (if (= (count fact-vector) 3)
      (new Fact (nth fact-vector 1) (str/split (nth fact-vector 2) #", "))
      nil)
    )
  )
