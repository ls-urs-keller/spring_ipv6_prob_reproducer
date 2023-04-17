Seems to be fixed from spring boot 3.0.4 if I bump the parent the problem goes away.

## Build
```
 ./mvnw install spring-boot:build-image
```

## Activate Ipv6 on docker
I tested this in mac and docker desktop you need to enable ipv6
![docker desktop ipv6.png](docs%2Fdocker%20desktop%20ipv6.png)


## Run test
```
docker run --user=root -d --rm --name service -ti ipv6reproducer:0.0.1-SNAPSHOT
docker exec -ti service sh -c 'apt-get -y update && apt-get install -y curl' >/dev/null
docker exec -ti service curl -H "Content-Type: application/json" 'http://localhost:8080/graphql' -d '{"query":"\nquery {\n  uri \n} \n\n"}'
docker logs service
docker stop service
```
