FROM maven:3.6.0-jdk-11-slim AS build

COPY src /home/app/src

COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package spring-boot:repackage

FROM openjdk:11-jre-slim

COPY target/CoffeeShop-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]