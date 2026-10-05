\# TaskTracker API



REST API for task management built with Spring Boot.



\*\*Status:\*\* 🚧 Work in Progress



!\[Version](https://img.shields.io/badge/version-0.1-blue)

!\[Java](https://img.shields.io/badge/Java-21-orange)

!\[Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1-green)

!\[Progress](https://img.shields.io/badge/progress-30%25-yellow)



\## About



A REST API for managing tasks with priorities, deadlines, and assignees.

Built with Spring Boot, Spring Data JPA, and H2 database.



\## Progress



\### Backend ✅

\- \[x] Spring Boot project setup

\- \[x] `Task` entity with JPA

\- \[x] `TaskRepository` (Spring Data JPA)

\- \[x] `TaskService` (business logic)

\- \[x] `TaskController` (REST API)

\- \[x] DTOs: `TaskRequest`, `TaskResponse`

\- \[x] H2 database integration

\- \[x] Query methods (`findByStatus`, `findByTitleContaining`)



\### Improvements 🚧

\- \[ ] Exception handling (`@ControllerAdvice`)

\- \[ ] Custom exceptions (`TaskNotFoundException`)

\- \[ ] Swagger/OpenAPI documentation

\- \[ ] Unit tests (`@SpringBootTest`, `MockMvc`)

\- \[ ] PostgreSQL (production database)

\- \[ ] Docker

\- \[ ] CI/CD



\## Technologies



\- Java 21

\- Spring Boot 4.1

\- Spring Data JPA

\- Hibernate 7.4

\- H2 Database

\- Gradle



\## API Endpoints



| Method | Endpoint | Description |

|--------|----------|-------------|

| `GET` | `/api/tasks` | Get all tasks |

| `GET` | `/api/tasks/{id}` | Get task by ID |

| `POST` | `/api/tasks` | Create a new task |

| `PUT` | `/api/tasks/{id}` | Update a task |

| `DELETE` | `/api/tasks/{id}` | Delete a task |

| `GET` | `/api/tasks/filter?status=NEW` | Filter by status |

| `GET` | `/api/tasks/search?keyword=Java` | Search by title |



\## How to run



```bash

git clone https://github.com/DANIL-lab1/task-tracker-api.git

cd task-tracker-api

./gradlew bootRun

```

# Version History

v0.1 - Initial REST API ✅

- Spring Boot project setup
- Task entity with JPA
- TaskRepository with query methods
- TaskService with business logic
- TaskController with REST endpoints
- DTOs for request/response
- H2 database integration

v1.0 - Production Ready ⏳

- Exception handling
- Swagger documentation
- Tests (unit + integration)
- PostgreSQL
- Docker
- CI/CD

# Author
DANIL-lab1

#License
MIT License