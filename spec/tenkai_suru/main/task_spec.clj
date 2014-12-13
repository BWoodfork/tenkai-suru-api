(ns tenkai-suru.main.task-spec
  (:require
    [speclj.core :refer :all]
    [tenkai-suru.utility.environment :refer [-env environment]]
    [tenkai-suru.main.task :refer :all]
    [tenkai-suru.spec-helper :refer [silence-logger!]]
    [tenkai-suru.utility.log :refer [info]]))


; switch to should-invoke
(def info-log-invocations (atom []))

(describe "core"
  (before (silence-logger!))
  (around [it]
    (let [original-env @-env]
      (it)
      (reset! -env original-env)))

  (it "logs when the task starts and completes"
    (with-redefs [info (fn [message] (reset! info-log-invocations (conj @info-log-invocations {:message message})))]
      (should=
        [{:message "Starting task-name in :environment"}
         {:message "Finished task-name in :environment"}]
        (run-task "task-name" :environment))))

  (it "sets the specified environment"
    (run-task "task-name" :some-arbitrary-env)
    (should= :some-arbitrary-env (environment))))
