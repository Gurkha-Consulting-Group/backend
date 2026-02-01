Docker instructions for the backend application

Build the Docker image (default uses Temurin 17 which is broadly available and compatible with Render):

    docker build -t gurkha-backend:latest .

Run the container (example with H2 in-memory DB):

    docker run --rm -p 8080:8080 gurkha-backend:latest

Override Java version (optional):
- To attempt to build using Java 25 images (only do this if those tags are available on your platform):

    docker build \
      --build-arg MAVEN_IMAGE=maven:3.9.6-eclipse-temurin-25 \
      --build-arg JRE_IMAGE=eclipse-temurin:25-jre \
      -t gurkha-backend:latest .

Notes:
- The project sets <java.version>25</java.version> in pom.xml. The Dockerfile defaults to Temurin 17 for better compatibility with Render and common CI/CD environments. If you need Java 25 features, ensure base images exist for your platform before overriding the build args above.
- To connect to Postgres, set SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, and SPRING_DATASOURCE_PASSWORD environment variables when running the container.
