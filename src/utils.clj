(ns utils
  (:require [clojure.string :as str]))

; 'fact(arg1, arg2,..., argn).'
(defn fact-regex
  "Returns the fact if it's valid, otherwise nil"
  [fact]
  (re-matches #"^([a-z]+)\(([[a-z]+[, [a-z]+]?]+)\)$" fact)
  )

;TODO :: Validar que los parametros de entrada sean los mismos que se evaluan despues del ":-".
; 'rule(X, Y) :- fact(X), fact(Y).'
(defn rule-regex
  "Returns the rule if it's valid, otherwise nil"
  [rule]
  (re-matches #"^([a-z]+)\(([[A-Z]+[, [A-Z]+]?]+)\) :- ([[a-z]+\([[A-Z]+[, [A-Z]+]?]+\)]+)$" rule)
  )

(defn fact-regex-rule
  "For facts inside rules. Returns the fact if it's valid, otherwise nil"
  [fact]
  (re-matches #"^([a-z]+)\(([[A-Z]+[, [A-Z]+]?]+)\)$" fact)
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
  (not (nil? (fact-regex query)))
    )
