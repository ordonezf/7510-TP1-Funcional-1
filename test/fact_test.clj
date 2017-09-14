(ns fact-test
  (:require [clojure.test :refer :all]
            [fact :refer :all]))


(def f (->Fact "a" ["b" "c"] ))
(def fr (->Fact "a" ["B" "C"] ))

(deftest fact-test
(testing "new fact creates the fact a(b, c)."
  (is (= (new-fact "a(b, c)")
         f)))
(testing "new fact returns nil when input fact is a(b, c."
  (is (= (new-fact "a(b, c")
         nil)))
(testing "new fact returns nil when input fact is a(B, C)."
  (is (= (new-fact "a(B, C)")
         nil)))
(testing "new fact for rule creates the fact a(B, C)."
  (is (= (new-fact-for-rule "a(B, C)")
         fr)))
(testing "new fact for rule returns nil when input fact is a(b, c)."
  (is (= (new-fact-for-rule "a(b, c)")
         nil)))
(testing "Simple new fact creates a fact a(b, c)"
  (is (= (simple-new-fact "a" ["b" "c"])
         f)))
)