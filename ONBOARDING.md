# Backend Onboarding

Welcome! This guide helps you get the backend running locally, understand profiles (H2 vs Supabase), and find key entry points.

## Checklist
- Install prerequisites
- Run locally with H2 (default)
- Run locally against Supabase (prod profile)
- Verify health endpoints
- Run tests

## Git: Clone and Pull
1. `git clone https://github.com/Gurkha-Consulting-Group/backend`
2. `cd backend`
3. `git checkout main`
4. `git pull`

## Prerequisites
- Java 17 (recommended for local build/runtime)
- Docker (optional, for container builds)
- Git

## Quick Start (Local H2)
The default profile is `local`, which uses an in-memory H2 database.

```bash
./mvnw spring-boot:run
```

H2 Console:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:contactrequestdb`
- User: `sa`
- Password: (empty)

## Run with Supabase (Prod Profile) from IntelliJ
Use the `prod` profile and set environment variables in your Run/Debug configuration.

Environment variables:
- `SPRING_PROFILES_ACTIVE=prod`
- `SPRING_DATASOURCE_URL=jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:5432/postgres?sslmode=require`
- `SPRING_DATASOURCE_USERNAME=<your-username>`
- `SPRING_DATASOURCE_PASSWORD=<your-password>`

Optional JVM option instead of env var:
- `-Dspring.profiles.active=prod`

## Run with Supabase (CLI)
```bash
SPRING_PROFILES_ACTIVE=prod \
SPRING_DATASOURCE_URL="jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:5432/postgres?sslmode=require" \
SPRING_DATASOURCE_USERNAME="<your-username>" \
SPRING_DATASOURCE_PASSWORD="<your-password>" \
./mvnw spring-boot:run
```

## Health Checks
- App health: `GET /api/health`
- DB health: `GET /api/db-health`

Example:
```bash
curl http://localhost:8080/api/health
curl http://localhost:8080/api/db-health
```

## API Endpoints
Base path: `/api`
- Contact requests: `/api/contact`
  - `GET /api/contact`
  - `POST /api/contact`
  - `GET /api/contact/{id}`
  - `PUT /api/contact/{id}`
  - `DELETE /api/contact/{id}`

## Profiles and Config Files
- Common config: `src/main/resources/application.properties`
- Local (H2): `src/main/resources/application-local.properties`
- Prod (Supabase): `src/main/resources/application-prod.properties`

Notes:
- `local` uses H2 and `ddl-auto=update`.
- `prod` uses Postgres and `ddl-auto=validate` (schema must exist).

## Database Schema
SQL files live in:
- `src/main/resources/schema.sql`
- `src/main/resources/data.sql`

If you change JPA entities, ensure the production schema matches (or use migrations).

## Docker (Optional)
The Docker image defaults to the `prod` profile.

```bash
docker build -t gurkha-backend:latest .
docker run --rm -p 8080:8080 gurkha-backend:latest
```

Override datasource in Docker (example):
```bash
docker run --rm -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:5432/postgres?sslmode=require" \
  -e SPRING_DATASOURCE_USERNAME="<your-username>" \
  -e SPRING_DATASOURCE_PASSWORD="<your-password>" \
  gurkha-backend:latest
```

## Tests
```bash
./mvnw test
```

## Key Code Paths
- App entry point: `src/main/java/com/gurkhaconsultinggroup/backend/BackendApplication.java`
- Controllers: `src/main/java/com/gurkhaconsultinggroup/backend/controller`
- Services: `src/main/java/com/gurkhaconsultinggroup/backend/service`
- Repositories: `src/main/java/com/gurkhaconsultinggroup/backend/repository`
- Models: `src/main/java/com/gurkhaconsultinggroup/backend/model`

## Troubleshooting
- Wrong database in use: check active profile in logs.
- Prod profile fails at startup: verify Supabase credentials and network access.
- Schema validation errors in prod: align DB schema with JPA entities.

## Related Docs
- `README.md`
- `README-Docker.md`
- `PROFILES-README.md`
