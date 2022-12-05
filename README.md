# SpringDocker
TP pour le cours Composant à l'uphf, nous avons utilisé Spring Boot comme backend et Redis en Base de données. 
Il n'y a pas de front, nous utilisons les commandes CURL pour lancer des requêtes.

Fait par KWIATEK Guyliane et STILLATUS Julien

-----------------

## Dockerfile

Voici le dockerfile qui permet de compiler et lancer le SpringBoot, que l'on peut retrouver dans les fichiers du GIT dans le dossier spring-boot-redis.

```
FROM maven:3.6.3-adoptopenjdk-11 as stage1
WORKDIR /opt/demo
COPY pom.xml .

RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean install -Dmaven.test.skip=true

FROM  adoptopenjdk/openjdk11:jre-11.0.9_11-alpine
WORKDIR /opt/demo
COPY --from=stage1 /opt/demo/target/*.jar /opt/demo.jar
EXPOSE 8085
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/opt/demo.jar"]
```

-----------------

## Docker Compose

Voici le docker compose pour lancer Redis et l'application SpringBoot, que l'on peut retrouver dans les fichiers du GIT dans le dossier spring-boot-redis.

```
version: '3'
services:
  app:
    build: .
    ports:
     - "8085:8085"
    links:
      - redis
  redis:
    image: redis
    command: redis-server --save 60 1
    volumes:
      - data:/data
    restart: always
    ports:
     - "6379:6379"
volumes:
  data:
```
  
-----------------

## Commandes

Les commandes CURL fonctionnent sur le cmd de windows et non le powershell (on a reussi seulement avec le cmd, le powershell de windows bloqué les requetes)
Voici les commandes CURL pour lancer une requête POST (on se sert du code ascii du caractère espace pour intégrer des espaces dans la chaine à hasher)

`curl -X POST http://localhost:8085/api/hash?string=ceci%20est%20un%20test`

Voici les commandes CURL pour lancer une requête GET (on se sert du code ascii du caractère espace pour intégrer des espaces dans la chaine à hasher)

`curl -X GET http://localhost:8085/api/hash?string=ceci%20est%20un%20test`

