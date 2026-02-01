Docker instructions for the backend application

Build the Docker image:

    docker build -t gurkha-backend:latest .

Run the container (example with H2 in-memory DB):

    docker run --rm -p 8080:8080 gurkha-backend:latest

Notes:
- The project sets <java.version>25</java.version> in pom.xml, but images for Java 25 may not be available. The Dockerfile uses Temurin 17 which is widely supported. If you need Java 25 features, update the Dockerfile base images accordingly.
- To connect to Postgres, set SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, and SPRING_DATASOURCE_PASSWORD environment variables when running the container.
