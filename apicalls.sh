curl localhost:9000/api/tasks
curl localhost:9000/api/tasks/5
curl -X POST -H content-type:application/json localhost:9000/api/tasks -d {"id":0, "text":"creating task via api"}
