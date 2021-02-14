##Sandbox Web Challenge

## Run application in docker
Package application:
```shell script
./gradlew build
```
Build and deploy application with docker:
```shell script
docker-compose up -d
```
Navigate to url in browser:
```shell script
http://localhost:8080/
```

## Run application locally
Build and deploy database:
```shell script
docker-compose -f src/main/docker/docker-compose-db.yml up -d
```
Navigate to url in browser:
```shell script
http://localhost:8080/
```


## Postman
You can find a postman collection under /postman.
Make use of it to test the application. 
