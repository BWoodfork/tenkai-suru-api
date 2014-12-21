(ns tenkai-suru.database.migrations
  (:require
    [ragtime.core :as rag]
    [ragtime.sql.files :as sql]))


(defn migrate-all [connection-uri migrations]
  (rag/migrate-all
    (rag/connection connection-uri)
    migrations))

(defn migrations [migrations-dir]
  (sql/migrations migrations-dir))

(defn rollback-last [connection-uri]
  (rag/rollback-last
    (rag/connection connection-uri)))
