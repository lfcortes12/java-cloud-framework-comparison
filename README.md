# java-cloud-framework-comparison


#Mongo DB useful commands

```nosql

-- Create database

-- Get db name
db.getName()

-- Show databases
show databases

-- Show collections
show collections

-- Use an specific database
use [databasename]

-- get elements in a collection
db.[collectionname].find()

-- get n elements in a collection
db.[collectionname].find().limit(n)

-- get a record by id
db.[collectionname].find({"_id":  ObjectId("5ee94fe7f0f2fc27b9d9f3ea")})


-- filtering by any field
db.score.find({"score": 324)})

-- exclude / include fields in the query result
db.score.find({"score": 324}, {"_class" : 0})

-- counting records
db.score.count()
db.score.count({"playerId": 7})

-- logout
db.logout()

``` 



-- To connect to mongo instance in rancher
kubectl run --namespace mongodb mongodb-client --rm --tty -i --restart='Never' --image bitnami/mongodb --command -- mongo admin --host mongodb --authenticationDatabase admin -u root -pCmoAITrQd0

-- Create a user in a database
use [new_db_name]
show databases -- it won't show the new database in the results if it does not have a collection
db.createUser({ user: "gamescoreusrquarkus", pwd: "gamescore", roles: [ { role: "readWrite", db: "gamescoredbquarkus" } ]})

db.grantRolesToUser("gamescoreusrquarkus",[{ role: "readWrite", db: "gamescoredbquarkus" }])

-- Get user info, that's is a query per database so connect to the database that you are interested.
db.getUser('gamescoreusrquarkus')

# Quarkus Deployment

## Create / Edit configmap
kubectl create configmap game-score-quarkus-cm --from-file=application.properties  -o yaml --dry-run | kubectl apply -f -
./gradlew quarkusBuild
 kubectl.exe apply -f build/kubernetes/kubernetes.yml
check it out https://10.54.228.22/quarkus/game-score/key 
 
 # Spring Deployment

## Spring Deployment / Edit configmap
kubectl create configmap spring-score --from-file=k8s/application.properties  -o yaml --dry-run | kubectl apply -f -
./gradlew quarkusBuild
 kubectl.exe apply -f build/kubernetes/kubernetes.yml
 
check it out https://10.54.228.22/spring/game-score/key
 
 
#References
https://cloud.spring.io/spring-cloud-static/spring-cloud-kubernetes/1.1.0.RELEASE/reference/html/#ribbon-discovery-in-kubernetes
 
 
 