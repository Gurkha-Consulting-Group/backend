# Multi-stage Dockerfile for building and running the Spring Boot application
# Use build args so we can target Java 25 by default but allow easy overrides
# Example to force Java 17 if 25 images are not available:
#  docker build \
#    --build-arg MAVEN_IMAGE=maven:3.9.6-eclipse-temurin-17 \
#    --build-arg JRE_IMAGE=eclipse-temurin:17-jre \
#    -t gurkha-backend:local .

ARG MAVEN_IMAGE="maven:3.9.6-eclipse-temurin-25"
ARG JRE_IMAGE="eclipse-temurin:25-jre"

# Builder stage: use Maven with Temurin 25 (user requested Java 25)
FROM ${MAVEN_IMAGE} AS builder
WORKDIR /workspace
# copy only what Maven needs first for dependency caching
COPY pom.xml mvnw ./
COPY .mvn .mvn
# copy src
COPY src ./src
# build the project (skip tests to speed up)
RUN mvn -B -DskipTests package

# Runtime stage: use the image specified by build arg
FROM ${JRE_IMAGE}
WORKDIR /app
ARG JAR_FILE=target/backend-0.0.1-SNAPSHOT.jar
COPY --from=builder /workspace/${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
