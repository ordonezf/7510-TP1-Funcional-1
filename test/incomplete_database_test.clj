(ns incomplete-database-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]
            [utils :as u]))

(def incomplete-database "
	varon(juan).
	varon
")

(deftest incomplete-database-fact-test
  (testing "varon(juan) should be nil"
    (is (= (evaluate-query incomplete-database "varon(juan)")
           nil))) 
  (testing "varon(maria) should be nil"
    (is (= (evaluate-query incomplete-database "varon(maria)")
           nil))) 
  (testing "mujer(cecilia) should be nil"
    (is (= (evaluate-query incomplete-database "mujer(cecilia)")
           nil))) 
  (testing "padre(juan, pepe) should be nil"
    (is (= (evaluate-query incomplete-database "padre(juan, pepe)")
           nil))) 
  (testing "padre(mario, pepe) should be nil"
    (is (= (evaluate-query incomplete-database "padre(mario, pepe)")
           nil))))

(deftest incomplete-database-rule-test
  (testing "hijo(pepe, juan) should be nil"
    (is (= (evaluate-query incomplete-database "hijo(pepe, juan)")
           nil))) 
  (testing "hija(maria, roberto) should be nil"
    (is (= (evaluate-query incomplete-database "hija(maria, roberto)")
           nil))))