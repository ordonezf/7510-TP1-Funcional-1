(ns query-runner-test
  (:require [clojure.test :refer :all]
            [database-maker :refer :all]
            [query-runner :refer :all]
            [fact :refer :all]))

(def db1 (create-database "
	varon(juan).
  mujer(maria).
  "))

(def db2 (create-database "
	varon(juan).
  mujer(maria).
  madre(X, Y) :- mujer(X), varon(Y).
  "))


(deftest query-runner-test
  (testing "Run varon(juan) against db1."
    (is (= (run-query "varon(juan)" db1)
              true)))
  (testing "Run varon(juan against db1."
    (is (= (run-query "varon(juan" db1)
           false)))
  (testing "Run madre(maria, juan) against db2."
    (is (= (run-query "madre(maria, juan)" db2)
           true)))
  (testing "madre(maria, juan) is valid"
    (is (= (evaluate-rule (new-fact "madre(maria, juan)") db2)
           true)))
  (testing "madre(juan, maria) is invalid"
    (is (= (evaluate-rule (new-fact "madre(juan, maria)") db2)
           false)))
  )