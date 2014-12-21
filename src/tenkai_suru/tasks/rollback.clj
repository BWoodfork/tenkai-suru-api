(ns tenkai-suru.tasks.rollback
  (:require
    [tenkai-suru.database.core :refer [database-connection-uri]]
    [tenkai-suru.database.migrations :refer [rollback-last]]
    [tenkai-suru.utility.environment :refer [environment]]
    [tenkai-suru.utility.log :refer [error]]))


(defn execute []
  (if-let [database-uri (database-connection-uri (environment))]
    (rollback-last database-uri)
    (error (str "database-uri not found for the given environment '" environment "'"))))
