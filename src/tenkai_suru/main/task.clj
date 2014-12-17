(ns tenkai-suru.main.task
  (:gen-class)
  (:require
    [environ.core :refer [env]]
    [tenkai-suru.utility.environment :refer [-env]]
    [tenkai-suru.utility.log :refer [config-logger! error info]]))


(defn run-task [task-name environment]
  (reset! -env environment)
  (info (str "Starting " task-name " in " environment))
  ; find and run the task

  (info (str "Finished " task-name " in " environment)))

(defn -main [& args]
  (let [task-name (first args)]
    (config-logger! {:to-standard-out? true :filename "task"})
    (if-let [environment (env :env)]
      (run-task task-name environment)
      (error (str "You must pass in the environment you wish to run " task-name " in as a profile. Example: lein with-profile development " task-name)))))
