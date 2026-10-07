\# Changelog



All notable changes to this project will be documented in this file.



The format is based on \[Keep a Changelog](https://keepachangelog.com/en/1.0.0/),

and this project adheres to \[Semantic Versioning](https://semver.org/spec/v2.0.0.html).



\## \[Unreleased]



\## \[1.0.0] - 2026-10-07



\### Added

\- REST API with 7 endpoints:

&#x20; - `GET /api/tasks` — get all tasks

&#x20; - `GET /api/tasks/{id}` — get task by ID

&#x20; - `POST /api/tasks` — create a task

&#x20; - `PUT /api/tasks/{id}` — update a task

&#x20; - `DELETE /api/tasks/{id}` — delete a task

&#x20; - `GET /api/tasks/filter?status=NEW` — filter by status

&#x20; - `GET /api/tasks/search?keyword=Java` — search by title

\- `Task` entity with JPA

\- `TaskRepository` (Spring Data JPA)

\- `TaskService` (business logic)

\- `TaskController` (REST API)

\- DTOs: `TaskRequest`, `TaskResponse`, `ErrorResponse`

\- Global exception handling (`@ControllerAdvice`)

\- Custom `TaskNotFoundException`

\- Validation errors handling (`@Valid`, `@NotBlank`)

\- Swagger/OpenAPI documentation

\- PostgreSQL 16 database

\- 22 unit + integration tests:

&#x20; - `TaskServiceTest` — 11 tests (Mockito)

&#x20; - `TaskControllerTest` — 10 tests (MockMvc)

&#x20; - `TaskTrackerApiApplicationTests` — 1 test

\- Docker + Docker Compose

\- GitHub Actions CI/CD



\### Technologies

\- Java 21

\- Spring Boot 4.1

\- Spring Data JPA

\- Hibernate 7.4

\- PostgreSQL 16

\- H2 (for tests)

\- Jackson 3

\- JUnit 5 + Mockito + MockMvc

\- Swagger/OpenAPI

\- Docker + Docker Compose

\- Gradle



\## \[0.6.0] - 2026-10-07



\### Added

\- Multi-stage `Dockerfile`

\- `docker-compose.yml` (app + postgres)

\- `.dockerignore`

\- PostgreSQL runs in container

\- API runs in container

\- Full stack with one command: `docker compose up`



\## \[0.5.0] - 2026-10-07



\### Added

\- PostgreSQL database (production)

\- `application-test.properties` for H2 (tests)

\- `@ActiveProfiles("test")` for test isolation

\- Created `taskuser` in PostgreSQL



\### Changed

\- Migrated from H2 to PostgreSQL



\## \[0.4.0] - 2026-10-06



\### Added

\- Unit tests for `TaskService` (Mockito, 11 tests)

\- Integration tests for `TaskController` (MockMvc, 10 tests)

\- Test logging configuration



\### Changed

\- Migrated to Spring Boot 4 test annotations:

&#x20; - `@MockBean` → `@MockitoBean`

&#x20; - `@AutoConfigureMockMvc` (new package)

\- Migrated to Jackson 3:

&#x20; - `com.fasterxml.jackson` → `tools.jackson`



\### Fixed

\- Test dependencies for Spring Boot 4



\## \[0.3.0] - 2026-10-06



\### Added

\- Swagger/OpenAPI integration (`springdoc-openapi`)

\- Interactive API documentation at `/swagger-ui.html`

\- `@Tag`, `@Operation`, `@ApiResponse` annotations

\- `@Schema` for DTOs with examples

\- `@Hidden` for exception handler



\## \[0.2.0] - 2026-10-06



\### Added

\- Global exception handling (`@ControllerAdvice`)

\- Custom `TaskNotFoundException`

\- Validation errors handling (`@Valid`)

\- Structured error responses (`ErrorResponse`)



\### Fixed

\- HTTP status codes: 404 (Not Found), 400 (Validation Failed), 500 (Internal Server Error)



\## \[0.1.0] - 2026-10-05



\### Added

\- Spring Boot project setup

\- `Task` entity with JPA

\- `TaskRepository` with query methods

\- `TaskService` with business logic

\- `TaskController` with REST endpoints

\- DTOs for request/response

\- H2 database integration



\[Unreleased]: https://github.com/DANIL-lab1/task-tracker-api/compare/v1.0.0...HEAD

\[1.0.0]: https://github.com/DANIL-lab1/task-tracker-api/compare/v0.6.0...v1.0.0

\[0.6.0]: https://github.com/DANIL-lab1/task-tracker-api/compare/v0.5.0...v0.6.0

\[0.5.0]: https://github.com/DANIL-lab1/task-tracker-api/compare/v0.4.0...v0.5.0

\[0.4.0]: https://github.com/DANIL-lab1/task-tracker-api/compare/v0.3.0...v0.4.0

\[0.3.0]: https://github.com/DANIL-lab1/task-tracker-api/compare/v0.2.0...v0.3.0

\[0.2.0]: https://github.com/DANIL-lab1/task-tracker-api/compare/v0.1.0...v0.2.0

\[0.1.0]: https://github.com/DANIL-lab1/task-tracker-api/releases/tag/v0.1.0

