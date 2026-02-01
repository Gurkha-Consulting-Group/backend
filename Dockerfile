# Multi-stage Dockerfile for building and running the Spring Boot application
# Builder stage: use Maven with Temurin 25 (user requested Java 25)
FROM maven:3.9.6-eclipse-temurin-25 AS builder
WORKDIR /workspace
# copy only what Maven needs first for dependency caching
COPY pom.xml mvnw ./
COPY .mvn .mvn
# copy src
COPY src ./src
# build the project (skip tests to speed up)
RUN mvn -B -DskipTests package

# Runtime stage: use Temurin 25 JRE
FROM eclipse-temurin:25-jre
WORKDIR /app
ARG JAR_FILE=target/backend-0.0.1-SNAPSHOT.jar
COPY --from=builder /workspace/${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
