# Multi-tenant PoC with DB per tenant

This PoC demonstrates a multi-tenant application with DB per tenant.

- Request will pass a header X-Tenant. This will decide which DB to connect. An error will be thrown 
  if the header is missing.
- An interceptor to intercept the request and extract the above header
- A thread safe context holder to store the Tenant ID. The ThreadLocal can be 
  considered as a scope of access, like a request scope or session scope.
- Customise SimpleMongoClientDatabaseFactory by extending. This will make sure 
  that each DB call is routed to a tenant specific DB.

> This POC is using in-memory store to save tenant specific connection details.
> In production this can be done using any Key-Value store DB like Redis.

## Pre requisite
- Docker
- Minikube - To run local kuberenetes
- kubectl - Kubernetes CLI
- Skaffold - To deploy and test in kbernetes
- Java SDK 11.x
- IDE (Intellij/ Eclipse)
- Mongodb

## Installation
* Install docker [https://docs.docker.com/engine/install/]
* Install minikube [https://minikube.sigs.k8s.io/docs/start/]
* Install kubectl [https://kubernetes.io/docs/tasks/tools/]
* install skaffold [https://skaffold.dev/docs/install/]

## Run minikube
To start minikube execute the following command
```
$ minikube start
```

Enable addon Ingress
```
$ minikube addons enable ingress
```

Enable load balancer, keep this programm running in a separate window
```
$ minikube tunnel
``` 


To test the application
- Run mongodb
- Open `k8s/deployment.yaml` file and set `MONGO_HOST` environment value.  
- Deploy the app
``` 
$ skaffold run 
```
- Run the following `curl` command. Set value of `X-Tenant` to `T1` or `T2`
```
curl --location --request POST 'http://localhost:8080/tenant/users' \
--header 'X-Tenant: T2' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName" : "Sunil",
    "lastName": "Ray"
}'
```
- Response will be an auto generated ID (64 bit Long number)