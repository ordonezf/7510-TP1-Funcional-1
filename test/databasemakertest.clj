(ns databasemakertest
  (:require [clojure.test :refer :all]
            [database-maker :refer :all]
            [fact :refer :all]
            [utils :refer :all]))

(def db1 "
	varon(juan).
  mujer(maria).
  ")

(def db2 "
	varon(juan).
  mujer(maria.
  ")

(def f (->Fact "varon" ["juan"]))

(deftest database-maker-test
  (testing "db1 creates a database"
    (is (not= (create-database db1)
           nil)))
  (testing "db1 creates a valid database"
    (is (= (is-valid-db?(create-database db1))
           true)))
  (testing "db2 creates a database"
    (is (= (create-database db2)
           {:rules (), :facts [f nil]})))
  (testing "db2 creates an invalid database"
    (is (= (is-valid-db?(create-database db2))
           nil)))
  )