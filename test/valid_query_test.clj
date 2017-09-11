(ns valid-query-test
  (:require [clojure.test :refer :all]
            [utils :as u]))

(deftest valid-query-fact
  (testing "hijo(pepe should be false")
  (is (= (u/is-valid-query? "hijo(pepe")
         false))
  (testing "hijo() should be false")
  (is (= (u/is-valid-query? "hijo()")
         false))
  (testing "hijo(pepe) should be true")
  (is (= (u/is-valid-query?  "hijo(pepe)")
         true))
  (testing "padre(pepe, juan) should be true")
  (is (= (u/is-valid-query?  "padre(pepe, juan)")
         true)))
