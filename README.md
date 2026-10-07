# TaskTracker API

[![CI](https://github.com/DANIL-lab1/task-tracker-api/actions/workflows/ci.yml/badge.svg)](https://github.com/DANIL-lab1/task-tracker-api/actions/workflows/ci.yml)
![Version](https://img.shields.io/badge/version-1.0-blue)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1-green)
![Tests](https://img.shields.io/badge/tests-22%20passed-green)

REST API for task management built with Spring Boot.

## About

A production-ready REST API for managing tasks with priorities, deadlines, and assignees.
Built with Spring Boot 4, Spring Data JPA, PostgreSQL, and containerized with Docker.

## Features

- **REST API** with 7 endpoints (CRUD + filter + search)
- **Task priorities** (`LOW`, `MEDIUM`, `HIGH`)
- **Statuses** (`NEW`, `IN_PROGRESS`, `DONE`)
- **Deadlines** with `LocalDateTime`
- **Assignees** (by name)
- **Filter by status**
- **Search by title**
- **Exception handling** with structured error responses
- **Validation** with `@Valid`, `@NotBlank`
- **Swagger/OpenAPI** documentation
- **PostgreSQL** database
- **Docker** + Docker Compose
- **CI/CD** with GitHub Actions

## Technologies

- Java 21
- Spring Boot 4.1
- Spring Data JPA
- Hibernate 7.4
- PostgreSQL 16
- H2 (for tests)
- Jackson 3
- JUnit 5 + Mockito + MockMvc
- SpringDoc OpenAPI (Swagger)
- Docker + Docker Compose
- GitHub Actions
- Gradle

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/tasks` | Get all tasks |
| `GET` | `/api/tasks/{id}` | Get task by ID |
| `POST` | `/api/tasks` | Create a new task |
| `PUT` | `/api/tasks/{id}` | Update a task |
| `DELETE` | `/api/tasks/{id}` | Delete a task |
| `GET` | `/api/tasks/filter?status=NEW` | Filter by status |
| `GET` | `/api/tasks/search?keyword=Java` | Search by title |

## API Errors

| Status | Error | When |
|--------|-------|------|
| `400` | Validation Failed | Invalid request body |
| `404` | Not Found | Task not found by ID |
| `500` | Internal Server Error | Unexpected error |

### Error Response Format

```json
{
  "timestamp": "2026-10-06T12:40:00",
  "status": 404,
  "error": "Not Found",
  "message": "Task not found: 550e8400-...",
  "path": "/api/tasks/550e8400-...",
  "validationErrors": null
}
```

## Swagger UI

Interactive API documentation is available at: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

You can:

- Browse all endpoints grouped by tags
- See request/response schemas with examples
- Test API directly in the browser using "Try it out"
- View all HTTP status codes for each endpoint

## Docker

Run the entire application stack (API + PostgreSQL) with Docker Compose:

```bash
docker compose up --build
```

This will: 

- Build the Spring Boot application
- Start PostgreSQL database
- Start the API on http://localhost:8080

### Service

| Service | Port | Description | 
| ------- | ---- | ----------- |
| `app` | 8080 | Spring Boot API |
| `postgres` | 5432 | PostgreSQL database | 

### Stop

```bash
docker compose down
```

### Stop and remove volumes

```bash
docker compose down -v
```

## Testing

```bash
# Run all tests
./gradlew test

# Run with detailed output
./gradlew clean test
```

### Test stats

| Test Class | Tests | Type |
|------------|-------|------|
| `TaskServiceTest` | 11 | Unit (Mockito) |
| `TaskControllerTest` | 10 | Integration (MockMvc) |
| `TaskTrackerApiApplicationTests` | 1 | Context load |
| **Total** | **22** | **All passing** ✅ |

### Test Coverage
- Service layer: CRUD, validation, exceptions
- Controller layer: All endpoints, status codes, error responses
- Validation: `@NotBlank`, `@Valid`
- Exception handling: 404, 400, 500

## How to run

### Option 1: Docker (recommended)

```bash
docker compose up --build
```

### Option 2: From source

```bash
git clone https://github.com/DANIL-lab1/task-tracker-api.git
cd task-tracker-api
./gradlew bootRun
```

The API will be available at http://localhost:8080.

### H2 Console

- **URL:** `http://localhost:8080/h2-console`
- JDBC URL: jdbc:h2:mem:testdb
- User: sa
- Password: (empty)

## Example Request

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Learn Spring Boot",
    "description": "Build TaskTracker API",
    "priority": "HIGH",
    "assigneeName": "DANIL-lab1"
  }'
```

## Project Structure

```
src/main/java/ru/tbank/task_tracker_api/
├── controller/       — REST controllers
├── dto/              — Data Transfer Objects
├── entity/           — JPA entities
├── exception/        — Exception handling
├── repository/       — Spring Data repositories
├── service/          — Business logic
└── TaskTrackerApiApplication.java

src/main/resources/
├── application.properties          — main config (PostgreSQL)
└── application-test.properties     — test config (H2)

src/test/java/ru/tbank/task_tracker_api/
├── controller/
│   └── TaskControllerTest.java     — integration tests
├── service/
│   └── TaskServiceTest.java        — unit tests
└── TaskTrackerApiApplicationTests.java
```

## Version History

v1.0 — Production Ready 🎉

- All features implemented
- PostgreSQL 16
- Docker + Docker Compose
- CI/CD with GitHub Actions
- 22 tests passing
- Swagger documentation
- Full README + CHANGELOG

v0.6 — Docker ✅
- Multi-stage `Dockerfile`
- `docker-compose.yml` (app + postgres)
- `.dockerignore`
- PostgreSQL runs in container
- API runs in container
- Full stack with one command: `docker compose up`

v0.5 — PostgreSQL ✅
- Migrated from H2 to PostgreSQL
- Added PostgreSQL driver
- Configured `application.properties` for PostgreSQL
- Added `application-test.properties` for H2 (tests)
- Added `@ActiveProfiles("test")` for test isolation
- Created `taskuser` in PostgreSQL
- All 22 tests passing

v0.4 — Tests ✅
- Unit tests for TaskService (Mockito, 11 tests)
- Integration tests for TaskController (MockMvc, 10 tests)
- Migrated to Spring Boot 4 test annotations (@MockitoBean, @AutoConfigureMockMvc)
- Migrated to Jackson 3 (tools.jackson.databind.ObjectMapper)
- Configured testLogging for detailed output
- All 22 tests passing

v0.3 — Swagger Documentation ✅

- Swagger/OpenAPI integration (springdoc-openapi)
- Interactive API documentation at /swagger-ui.html
- @Tag, @Operation, @ApiResponse annotations
- @Schema for DTOs with examples
- @Hidden for exception handler

v0.2 — Exception Handling ✅

- Global exception handling (@ControllerAdvice)
- Custom TaskNotFoundException
- Validation errors handling (@Valid)
- Structured error responses (ErrorResponse)

v0.1 - Initial REST API ✅

- Spring Boot project setup
- Task entity with JPA
- TaskRepository with query methods
- TaskService with business logic
- TaskController with REST endpoints
- DTOs for request/response
- H2 database integration

## Roadmap

- ✅ v1.0 — Production Ready
- ⏳ Next: CI/CD improvements, monitoring
- ⏳ Future: Microservices, Kafka

## Author
DANIL-lab1

## License
MIT License
