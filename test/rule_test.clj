(ns rule-test
  (:require [clojure.test :refer :all]
            [rule :refer :all]))


(deftest rule-test
  (testing "new rule creates the rule a(B, C) :- z(C, B)."
    (is (not= (new-rule "a(B, C) :- z(C, B)")
           nil)))
  (testing "new rule fails to create the rule a(b, C) :- z(C, B)."
    (is (= (new-rule "a(b, C) :- z(C, B)")
              nil)))
  (testing "new rule fails to create the rule a(B, C)."
    (is (= (new-rule "a(B, C)")
           nil)))
  )