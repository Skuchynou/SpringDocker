#Stage 1
# initialize build and set base image for first stage
FROM maven:3.6.3-adoptopenjdk-11 as stage1
WORKDIR /opt/demo
COPY pom.xml .

RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean install -Dmaven.test.skip=true

FROM  adoptopenjdk/openjdk11:jre-11.0.9_11-alpine
WORKDIR /opt/demo
COPY --from=stage1 /opt/demo/target/*.jar /opt/demo.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/opt/demo.jar"]