FROM eclipse-temurin:21-jre-alpine

LABEL authors="Ira"

WORKDIR /app

COPY ./target/demo1-0.0.1-SNAPSHOT.jar ./demo1.jar

ENV MONGODB_PASSWORD=""

ENV MONGODB_USER=user

ENV MONGODB_BASE=test

ENTRYPOINT ["java", "-jar", "/app/demo1.jar"]