# Multi-stage Dockerfile for building and running the Spring Boot application
# Builder stage: use Maven with a matching JDK (Temurin 17 is widely available and compatible)
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /workspace
# copy only what Maven needs first for dependency caching
COPY pom.xml mvnw ./
COPY .mvn .mvn
# copy src
COPY src ./src
# build the project (skip tests to speed up)
RUN mvn -B -DskipTests package

# Runtime stage: smaller JRE image
FROM eclipse-temurin:17-jre
WORKDIR /app
ARG JAR_FILE=target/backend-0.0.1-SNAPSHOT.jar
COPY --from=builder /workspace/${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
