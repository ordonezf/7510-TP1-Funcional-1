(ns utils
  (:require [clojure.string :as str]))

; 'fact(arg1, arg2, ..., argn)'.
(def fact-regex "^([a-z]+)\\(((?:[a-z]+, )*[a-z]+)\\)$")
; 'fact(ARG1, ARG2, ..., ARGN)'.
(def fact-in-rule-regex "^([a-z]+)\\(((?:[A-Z]+, )*[A-Z]+)\\)$")
; 'rule(ARGS) :- facts(ARGS)'.
(def rule-regex "^([a-z]+)\\(((?:[A-Z]+, )*[A-Z]+)\\) :- ([([a-z]+)\\(((?:[A-Z]+, )*[A-Z]+)\\)]+)$")

(defn regex-match
  "Runs the regex against the string"
  [regex string]
  (re-matches (re-pattern regex) string)
  )

(defn is-rule?
  [rule]
  (if (str/includes? rule ":-") true false))

(defn is-fact?
  [fact]
  (if (str/includes? fact ":-") false true)
  )

(defn is-valid-db?
  "Returns nil if there is a nil value in the database, else it returns true."
  [db]
  (if (some nil? (:rules db))
    nil
    (if (some nil? (:facts db))
      nil
      true))
  )

(defn is-valid-query?
  "Returns True if the query is valid, else false."
  [query]
  (not (nil? (regex-match fact-regex query)))
    )

(defn query-has-correct-args?
  "Returns true if the query has valid arguments"
  [query rule]
  (= (count (:args query)) (count (:args rule)))
  )

(defn argument-dictionary
  "Maps the arguments of the query with the arguments of the rule."
  [rule-args fact-args]
  (map #(get rule-args %) fact-args)
  )

(defn is-fact-true?
  "Looks for the fact in the fact-db, if it exists it returns true, else false."
  [fact fact-db]
  (= (count (filter #(= % fact) fact-db)) 1)
  )