Docker instructions for the backend application

Build the Docker image:

    docker build -t gurkha-backend:latest .

Run the container (example with H2 in-memory DB):

    docker run --rm -p 8080:8080 gurkha-backend:latest

Notes:
- The project sets <java.version>25</java.version> in pom.xml and the Dockerfile now uses Temurin 25 images. Ensure the `maven:3.9.6-eclipse-temurin-25` and `eclipse-temurin:25-jre` images exist for your Docker platform; if they do not, either install a compatible JDK in the image or change to a supported tag (for example `-temurin-17`).
- To connect to Postgres, set SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, and SPRING_DATASOURCE_PASSWORD environment variables when running the container.
