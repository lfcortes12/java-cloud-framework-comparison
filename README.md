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
kubectl create configmap game-score-spring-cm --from-file=k8s/application.properties  -o yaml --dry-run | kubectl apply -f -
./gradlew quarkusBuild
 kubectl.exe apply -f build/kubernetes/kubernetes.yml
 
check it out https://10.54.228.22/spring/game-score/key

# AWS
Install helm
curl https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 > get_helm.sh
chmod 700 get_helm.sh
./get_helm.sh
helm help

add repo
helm install bitnami/mongodb --generate-name

helm install mongodbgamescore \
    --set auth.rootPassword=June2020.,auth.username=gamescoreusrspring,auth.password=gamescore,auth.database=springgamescoredb,architecture=replicaset \
    bitnami/mongodb

NAME: mongodbgamescore
LAST DEPLOYED: Mon Jul  6 06:53:19 2020
NAMESPACE: default
STATUS: deployed
REVISION: 1
TEST SUITE: None
NOTES:
** Please be patient while the chart is being deployed **

MongoDB can be accessed via port 27017 on the following DNS name(s) from within your cluster:

    mongodbgamescore-0.mongodbgamescore-headless.default.svc.cluster.local
    mongodbgamescore-1.mongodbgamescore-headless.default.svc.cluster.local


To get the root password run:

    export MONGODB_ROOT_PASSWORD=$(kubectl get secret --namespace default mongodbgamescore -o jsonpath="{.data.mongodb-root-password}" | base64 --decode)

To get the password for "gamescoreusrspring" run:

    export MONGODB_PASSWORD=$(kubectl get secret --namespace default mongodbgamescore -o jsonpath="{.data.mongodb-password}" | base64 --decode)

To connect to your database, create a MongoDB client container:

    kubectl run --namespace default mongodbgamescore-client --rm --tty -i --restart='Never' --image docker.io/bitnami/mongodb:4.2.8-debian-10-r21 --command -- bash

Then, run the following command:
    mongo admin --host "mongodbgamescore-0.mongodbgamescore-headless.default.svc.cluster.local,mongodbgamescore-1.mongodbgamescore-headless.default.svc.cluster.local," --authenticationDatabase admin -u root -p $MONGODB_ROOT_PASSWORD
	
-- show helm installed charts
helm list
-- to remove a chart
helm uninstall [chartname]


	
	
Deploy CM

kubectl create configmap game-score-spring-cm --from-file=/mnt/c/dev/git/java-cloud-framework-comparison/spring-game-score/k8s/application.properties  -o yaml --dry-run | kubectl apply -f -


db.createUser({ user: "gamescoreusrspring", pwd: "gamescore", roles: [ { role: "readWrite", db: "database" } ]})


Delete deployment
kubectl delete -f /mnt/c/dev/git/java-cloud-framework-comparison/spring-game-score/k8s/deployment.yaml

Install eksctl https://docs.aws.amazon.com/eks/latest/userguide/eksctl.html#installing-eksctl


eksctl create cluster \
 --name game-score \
 --version 1.16 \
 --without-nodegroup
 
 
 eksctl create nodegroup \
--cluster game-score \
--version auto \
--name standard-workers \
--node-type t2.micro \
--node-ami auto \
--nodes 3 \
--nodes-min 1 \
--nodes-max 4
 
 
 

eksctl utils associate-iam-oidc-provider \
    --region us-east-1 \
    --cluster game-score \
    --approve

curl -o iam-policy.json https://raw.githubusercontent.com/kubernetes-sigs/aws-alb-ingress-controller/v1.1.4/docs/examples/iam-policy.json


aws iam create-policy \
    --policy-name ALBIngressControllerIAMPolicy \
    --policy-document file:///mnt/c/dev/git/java-cloud-framework-comparison/spring-game-score/k8s/iam-policy.json
	
ARN policy returned was:

{
    "Policy": {
        "PolicyName": "ALBIngressControllerIAMPolicy",
        "PolicyId": "ANPA3GBC6YY6UABQPX57O",
        "Arn": "arn:aws:iam::768872465981:policy/ALBIngressControllerIAMPolicy",
        "Path": "/",
        "DefaultVersionId": "v1",
        "AttachmentCount": 0,
        "PermissionsBoundaryUsageCount": 0,
        "IsAttachable": true,
        "CreateDate": "2020-07-06T13:17:34+00:00",
        "UpdateDate": "2020-07-06T13:17:34+00:00"
    }
}


kubectl apply -f https://raw.githubusercontent.com/kubernetes-sigs/aws-alb-ingress-controller/v1.1.4/docs/examples/rbac-role.yaml

--below performs for clusters created with eksctl  https://github.com/weaveworks/eksctl/issues/1930
eksctl create iamserviceaccount \
    --region us-east-1 \
    --name alb-ingress-controller \
    --namespace kube-system \
    --cluster game-score \
    --attach-policy-arn arn:aws:iam::768872465981:policy/ALBIngressControllerIAMPolicy \
    --override-existing-serviceaccounts \
    --approve
	

kubectl annotate serviceaccount -n kube-system alb-ingress-controller \
eks.amazonaws.com/role-arn=arn:aws:iam::768872465981:role/eks-alb-ingress-controller


curl -sS "https://raw.githubusercontent.com/kubernetes-sigs/aws-alb-ingress-controller/v1.1.4/docs/examples/alb-ingress-controller.yaml" \
     | sed "s/# - --cluster-name=devCluster/- --cluster-name=game-score/g" \
	 | sed "s/# - --aws-region=us-west-1/- --aws-region=us-east-1/g" \
	 | sed "s/# - --aws-vpc-id=vpc-xxxxxx/- --aws-vpc-id=vpc-0d8e57eddcf4b6777/g" \
     | kubectl apply -f -
	 
	 
#GCP Deployment
## MongoDB

helm install mongodbgamescore \
    --set auth.rootPassword=June2020.,auth.username=gamescoreusrspring,auth.password=gamescore,auth.database=springgamescoredb,architecture=replicaset \
    bitnami/mongodb
	
	
NAME: mongodbgamescore
LAST DEPLOYED: Thu Jul 16 12:45:08 2020
NAMESPACE: default
STATUS: deployed
REVISION: 1
TEST SUITE: None
NOTES:
** Please be patient while the chart is being deployed **

MongoDB can be accessed via port 27017 on the following DNS name(s) from within your cluster:

    mongodbgamescore-0.mongodbgamescore-headless.default.svc.cluster.local
    mongodbgamescore-1.mongodbgamescore-headless.default.svc.cluster.local


To get the root password run:

    export MONGODB_ROOT_PASSWORD=$(kubectl get secret --namespace default mongodbgamescore -o jsonpath="{.data.mongodb-root-password}" | base64 --decode)

To get the password for "gamescoreusrspring" run:

    export MONGODB_PASSWORD=$(kubectl get secret --namespace default mongodbgamescore -o jsonpath="{.data.mongodb-password}" | base64 --decode)

To connect to your database, create a MongoDB client container:

    kubectl run --namespace default mongodbgamescore-client --rm --tty -i --restart='Never' --image docker.io/bitnami/mongodb:4.2.8-debian-10-r21 --command -- bash

Then, run the following command:
    mongo admin --host "mongodbgamescore-0.mongodbgamescore-headless.default.svc.cluster.local,mongodbgamescore-1.mongodbgamescore-headless.default.svc.cluster.local," --authenticationDatabase admin -u root -p $MONGODB_ROOT_PASSWORD
	
	
## Create configmap
 kubectl create configmap game-score-spring-cm --from-file=k8s/application-gcp.properties  -o yaml --dry-run | kubectl apply -f -

 
#References
https://cloud.spring.io/spring-cloud-static/spring-cloud-kubernetes/1.1.0.RELEASE/reference/html/#ribbon-discovery-in-kubernetes
 
 
 
 
 