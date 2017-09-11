(ns fact-database-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]
            [rule :as r]))

(def fact-database "
	varon(juan).
  mujer(maria).
  ")

(deftest fact-database-test
  (testing "varon(juan). should be true"
    (is (= (evaluate-query fact-database "varon(juan).")
           true)))
  (testing "mujer(maria). should be true"
    (is (= (evaluate-query fact-database "mujer(maria).")
           true)))
  (testing "mujer(pedro). should be false"
    (is (= (evaluate-query fact-database "mujer(pedro).")
           false))))



(def rule "
  hija(X, Y) :- mujer(X), padre(Y, X).")

(deftest rule123
  (testing "que funcione"
    (is (= r/new-rule rule) true)))