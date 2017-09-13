(ns logical-interpreter
  (:require [database-maker :as dbm]
            [utils :as u]
            [query-runner :as qr]
            ))

(defn evaluate-query
  "Returns true if the rules and facts in database imply query, false if not. If
  either input can't be parsed, returns nil"
  [database query]
  (let [db (dbm/create-database database)]
    (if (and (u/is-valid-db? db) (u/is-valid-query? query))
      (qr/run-query query db)
      nil
    ))
  )
