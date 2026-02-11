Docker instructions for the backend application

Build the Docker image (default uses Temurin 17 which is broadly available and compatible with Render):

    docker build -t gurkha-backend:latest .

Run the container (the image activates the `prod` Spring profile by default):

    docker run --rm -p 8080:8080 gurkha-backend:latest

Notes on production DB and testing locally
- The image sets SPRING_PROFILES_ACTIVE=prod, which tells Spring Boot to load `application-prod.properties` (this file already contains your Supabase production DB connection settings).
- For Render (or any production environment) you should NOT bake credentials into the image. Instead set environment variables or secrets in the service to override the datasource values, for example in Render's dashboard set:
  - SPRING_DATASOURCE_URL
  - SPRING_DATASOURCE_USERNAME
  - SPRING_DATASOURCE_PASSWORD

- To test locally without connecting to the Supabase DB, override the datasource environment variables to use an H2 memory DB or your local Postgres instance. Example (use H2 in-memory to verify startup):

    docker run --rm -p 8080:8080 \
      -e SPRING_DATASOURCE_URL=jdbc:h2:mem:contactrequestdb \
      -e SPRING_DATASOURCE_DRIVER-CLASS-NAME=org.h2.Driver \
      -e SPRING_DATASOURCE_USERNAME=sa \
      -e SPRING_DATASOURCE_PASSWORD= \
      gurkha-backend:latest

Override active profile (if needed):
- To run a different profile (e.g., `default`), pass:

    docker run --rm -p 8080:8080 -e SPRING_PROFILES_ACTIVE=default gurkha-backend:latest

Security note
- Remove hard-coded production credentials from `application-prod.properties` and use environment variables in Render (or your deployment platform) to inject secrets. I can help migrate credentials to environment variables if you'd like.

Override Java version (optional):
- To attempt to build using Java 25 images (only do this if those tags are available on your platform):

    docker build \
      --build-arg MAVEN_IMAGE=maven:3.9.6-eclipse-temurin-25 \
      --build-arg JRE_IMAGE=eclipse-temurin:25-jre \
      -t gurkha-backend:latest .

Notes:
- The project sets <java.version>25</java.version> in pom.xml. The Dockerfile defaults to Temurin 17 for better compatibility with Render and common CI/CD environments. If you need Java 25 features, ensure base images exist for your platform before overriding the build args above.
- To connect to Postgres, set SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, and SPRING_DATASOURCE_PASSWORD environment variables when running the container.
