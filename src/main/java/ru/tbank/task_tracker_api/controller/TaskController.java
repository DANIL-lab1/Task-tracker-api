package ru.tbank.task_tracker_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.tbank.task_tracker_api.dto.TaskRequest;
import ru.tbank.task_tracker_api.dto.TaskResponse;
import ru.tbank.task_tracker_api.entity.Status;
import ru.tbank.task_tracker_api.entity.Task;
import ru.tbank.task_tracker_api.service.TaskService;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for task management.
 *
 * @author DANIL-lab1
 * @version 1.0
 */
@RestController
@RequestMapping("/api/tasks")
@Tag(
    name = "Tasks",
    description = "Task management API: create, read, update, delete, filter, search"
)
public class TaskController {
    
    private final TaskService service;
    
    public TaskController(TaskService service) {
        this.service = service;
    }
    
    @GetMapping
    @Operation(
        summary = "Get all tasks",
        description = "Returns a list of all tasks in the system"
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all tasks")
    public List<TaskResponse> getAllTasks() {
        return service.getAllTasks().stream()
            .map(this::toResponse)
            .toList();
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Get task by ID",
        description = "Returns a single task by its unique identifier"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Task found"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public TaskResponse getTaskById(
        @Parameter(description = "Task UUID", example = "550e8400-e29b-41d4-a716-446655440000")
        @PathVariable UUID id
    ) {
        return toResponse(service.getTaskById(id));
    }
    
    @PostMapping
    @Operation(
        summary = "Create a new task",
        description = "Creates a new task and returns it with generated ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Task created successfully"),
        @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody TaskRequest request) {
        Task task = new Task(request.getTitle(), request.getDescription());
        if (request.getStatus() != null) task.setStatus(request.getStatus());
        if (request.getPriority() != null) task.setPriority(request.getPriority());
        task.setAssigneeName(request.getAssigneeName());
        task.setDeadline(request.getDeadline());
        
        return toResponse(service.createTask(task));
    }
    
    @PutMapping("/{id}")
    @Operation(
        summary = "Update a task",
        description = "Updates an existing task by ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Task updated"),
        @ApiResponse(responseCode = "404", description = "Task not found"),
        @ApiResponse(responseCode = "400", description = "Validation failed")
    })
    public TaskResponse updateTask(
        @Parameter(description = "Task UUID", example = "550e8400-e29b-41d4-a716-446655440000")
        @PathVariable UUID id,
        @Valid @RequestBody TaskRequest request
    ) {
        Task task = new Task(request.getTitle(), request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setAssigneeName(request.getAssigneeName());
        task.setDeadline(request.getDeadline());
        
        return toResponse(service.updateTask(id, task));
    }
    
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a task",
        description = "Deletes a task by ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Task deleted"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(
        @Parameter(description = "Task UUID", example = "550e8400-e29b-41d4-a716-446655440000")
        @PathVariable UUID id
    ) {
        service.deleteTask(id);
    }
    
    @GetMapping("/filter")
    @Operation(
        summary = "Filter tasks by status",
        description = "Returns all tasks with the given status (NEW, IN_PROGRESS, DONE)"
    )
    @ApiResponse(responseCode = "200", description = "Successfully filtered")
    public List<TaskResponse> filterByStatus(
        @Parameter(description = "Task status", example = "NEW")
        @RequestParam Status status
    ) {
        return service.getTasksByStatus(status).stream()
            .map(this::toResponse)
            .toList();
    }
    
    @GetMapping("/search")
    @Operation(
        summary = "Search tasks by title",
        description = "Returns all tasks whose title contains the keyword (case-insensitive)"
    )
    @ApiResponse(responseCode = "200", description = "Successfully searched")
    public List<TaskResponse> search(
        @Parameter(description = "Search keyword", example = "Java")
        @RequestParam String keyword
    ) {
        return service.searchByTitle(keyword).stream()
            .map(this::toResponse)
            .toList();
    }
    
    private TaskResponse toResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setPriority(task.getPriority());
        response.setAssigneeName(task.getAssigneeName());
        response.setDeadline(task.getDeadline());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        return response;
    }
}