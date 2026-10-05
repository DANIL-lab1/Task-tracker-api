package ru.tbank.task_tracker_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.task_tracker_api.dto.TaskRequest;
import ru.tbank.task_tracker_api.dto.TaskResponse;
import ru.tbank.task_tracker_api.entity.Status;
import ru.tbank.task_tracker_api.entity.Task;
import ru.tbank.task_tracker_api.repository.TaskService;

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
public class TaskController {
    
    private final TaskService service;
    
    /**
     * Creates a new controller with the given service.
     *
     * @param service the task service
     */
    public TaskController(TaskService service) {
        this.service = service;
    }
    
    /**
     * Returns all tasks.
     *
     * @return list of tasks
     */
    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return service.getAllTasks().stream()
            .map(this::toResponse)
            .toList();
    }
    
    /**
     * Finds a task by ID.
     *
     * @param id the task ID
     * @return the task
     */
    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable UUID id) {
        return toResponse(service.getTaskById(id));
    }
    
    /**
     * Creates a new task.
     *
     * @param request the task data
     * @return the created task
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody TaskRequest request) {
        Task task = new Task(request.getTitle(), request.getDescription());
        if (request.getStatus() != null) task.setStatus(request.getStatus());
        if (request.getPriority() != null) task.setPriority(request.getPriority());
        task.setAssigneeName(request.getAssigneeName());
        task.setDeadline(request.getDeadline());
        
        return toResponse(service.createTask(task));
    }
    
    /**
     * Updates an existing task.
     *
     * @param id      the task ID
     * @param request the updated data
     * @return the updated task
     */
    @PutMapping("/{id}")
    public TaskResponse updateTask(
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
    
    /**
     * Deletes a task.
     *
     * @param id the task ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable UUID id) {
        service.deleteTask(id);
    }
    
    /**
     * Filters tasks by status.
     *
     * @param status the status
     * @return list of tasks
     */
    @GetMapping("/filter")
    public List<TaskResponse> filterByStatus(@RequestParam Status status) {
        return service.getTasksByStatus(status).stream()
            .map(this::toResponse)
            .toList();
    }
    
    /**
     * Searches tasks by title keyword.
     *
     * @param keyword the search keyword
     * @return list of tasks
     */
    @GetMapping("/search")
    public List<TaskResponse> search(@RequestParam String keyword) {
        return service.searchByTitle(keyword).stream()
            .map(this::toResponse)
            .toList();
    }
    
    /**
     * Converts a Task entity to a TaskResponse DTO.
     *
     * @param task the task entity
     * @return the response DTO
     */
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
