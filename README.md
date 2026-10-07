# TaskTracker API

REST API for task management built with Spring Boot.

**Status:** 🚧 Work in Progress

![Version](https://img.shields.io/badge/version-0.5-blue)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1-green)
![Tests](https://img.shields.io/badge/tests-22%20passed-green)
![Progress](https://img.shields.io/badge/progress-80%25-yellow)

## About

A REST API for managing tasks with priorities, deadlines, and assignees.
Built with Spring Boot, Spring Data JPA, and H2 database.

## Progress

### Backend ✅
- [x] Spring Boot project setup
- [x] `Task` entity with JPA
- [x] `TaskRepository` (Spring Data JPA)
- [x] `TaskService` (business logic)
- [x] `TaskController` (REST API)
- [x] DTOs: `TaskRequest`, `TaskResponse`
- [x] H2 database integration
- [x] Query methods (`findByStatus`, `findByTitleContaining`)

### Improvements 🚧
- [x] Exception handling (`@ControllerAdvice`)
- [x] Custom exceptions (`TaskNotFoundException`)
- [x] Validation errors handling
- [x] Swagger/OpenAPI documentation
- [x] Unit tests (Mockito) — 11 tests
- [x] Integration tests (MockMvc) — 10 tests
- [x] PostgreSQL (production database)
- [ ] Docker
- [ ] CI/CD

## Technologies

- Java 21
- Spring Boot 4.1
- Spring Data JPA
- Hibernate 7.4
- H2 Database
- SpringDoc OpenAPI (Swagger)
- JUnit 5 + Mockito + MockMvc
- Jackson 3
- Gradle
- PostgreSQL 16
- H2 (for tests)

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

## Testing

```bash
# Run all tests
./gradlew test

# Run with detailed output
./gradlew clean test
```

### Test stats:

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

```bash
git clone https://github.com/DANIL-lab1/task-tracker-api.git
cd task-tracker-api
./gradlew bootRun
```

The API will be available at http://localhost:8080.

## H2 Console

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

## Version History

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

v1.0 - Production Ready ⏳
- PostgreSQL
- Docker
- CI/CD

## Author
DANIL-lab1

## License
MIT License
