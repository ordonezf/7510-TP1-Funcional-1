(ns utils-test
  (:require [clojure.test :refer :all]
            [utils :refer :all]))




(deftest regex-test
  (testing "Passing a string and a regex to regex-match returns desired match."
    (is (= (regex-match #"[a-z]+\." "palabra.")
           "palabra.")))
  (testing "Passing a string and a regex to regex-match returns nil when string doesn't match."
    (is (= (regex-match #"[a-z]+" "palabra.")
           nil)))
  (testing "fact-regex returns nil when fact is 'varon(juan.'"
    (is (= (regex-match fact-regex "varon(juan.")
           nil)))
  (testing "fact-regex returns nil when fact is'varon(juan,)'"
    (is (= (regex-match fact-regex "varon(juan,)")
           nil)))
  (testing "fact-regex returns nil when fact is'a(b, c, )'"
    (is (= (regex-match fact-regex "a(b, c, )")
           nil)))
  (testing "fact-regex returns nil when fact is'a(b, c'"
    (is (= (regex-match fact-regex "a(b, c")
           nil)))
  (testing "fact-regex returns nil when fact is'a(b, C)'"
    (is (= (regex-match fact-regex "a(b, C")
           nil)))
  (testing "fact-regex returns the parsed fact when fact is'a(b, c)'"
    (is (= (regex-match fact-regex "a(b, c)")
           ["a(b, c)" "a" "b, c"])))
  (testing "rule-regex returns the parsed rule when rule is'a(B, C) :- z(B, C)'"
    (is (= (regex-match rule-regex "a(B, C) :- z(B, C)")
           ["a(B, C) :- z(B, C)" "a" "B, C" "z(B, C)"])))
  (testing "rule-regex returns nil when rule is'a(B, c) :- z(B, C)'"
    (is (= (regex-match rule-regex "a(B, c) :- z(B, C)")
           nil)))
  )

(deftest is-rule-is-fact-test
  (testing "a(b, c) is a fact"
    (is (= (is-fact? "a(b, c)")
           true)))
  (testing "a(b, c) is not a rule"
    (is (= (is-rule? "a(b, c)")
           false)))
  (testing "a(b, c) :- x(c, b) is not a fact"
    (is (= (is-fact? "a(b, c) :- x(c, b)")
           false)))
  (testing "a(b, c) :- x(c, b) is a rule"
    (is (= (is-rule? "a(b, c) :- x(c, b)")
           true)))
  )
(deftest argument-dictionary-test
  (testing "Map rule arguments with query arguments."
    (is (= (argument-dictionary {"X" "A", "Y" "B"} ["Y" "X"])
           ["B" "A"])))
  (testing "Arguments are invalid"
    (is (= (argument-dictionary {"X" "A", "Y" "B"} ["Z" "X"])
           [nil "A"])))
  )