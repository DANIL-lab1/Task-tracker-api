package ru.tbank.task_tracker_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.tbank.task_tracker_api.entity.Priority;
import ru.tbank.task_tracker_api.entity.Status;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Response DTO for task data.
 * <p>
 * Used to return task information via REST API.
 *
 * @author DANIL-lab1
 * @version 1.0
 */
@Schema(description = "Task response with full task data")
public class TaskResponse {
    
    @Schema(description = "Task UUID", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;
    
    @Schema(description = "Task title", example = "Learn Spring Boot")
    private String title;
    
    @Schema(description = "Task description", example = "Build TaskTracker API")
    private String description;
    
    @Schema(description = "Task status", example = "NEW")
    private Status status;
    
    @Schema(description = "Task priority", example = "HIGH")
    private Priority priority;
    
    @Schema(description = "Assignee name", example = "DANIL-lab1")
    private String assigneeName;
    
    @Schema(description = "Deadline", example = "2026-12-31T23:59:59")
    private LocalDateTime deadline;
    
    @Schema(description = "Creation timestamp", example = "2026-10-06T12:40:00")
    private LocalDateTime createdAt;
    
    @Schema(description = "Last update timestamp", example = "2026-10-06T12:40:00")
    private LocalDateTime updatedAt;
    
    public TaskResponse() {}
    
    // Getters and Setters
    
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    
    public String getAssigneeName() { return assigneeName; }
    public void setAssigneeName(String assigneeName) { this.assigneeName = assigneeName; }
    
    public LocalDateTime getDeadline() { return deadline; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
