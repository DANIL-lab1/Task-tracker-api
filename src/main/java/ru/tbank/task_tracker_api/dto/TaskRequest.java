package ru.tbank.task_tracker_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import ru.tbank.task_tracker_api.entity.Priority;
import ru.tbank.task_tracker_api.entity.Status;

import java.time.LocalDateTime;

/**
 * Request DTO for creating or updating a task.
 *
 * @author DANIL-lab1
 * @version 1.0
 */
@Schema(description = "Request to create or update a task")
public class TaskRequest {
    
    @Schema(
        description = "Task title",
        example = "Learn Spring Boot",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Title cannot be empty")
    private String title;
    
    @Schema(
        description = "Task description",
        example = "Build TaskTracker API with Spring Boot"
    )
    private String description;
    
    @Schema(
        description = "Task status",
        example = "NEW",
        allowableValues = {"NEW", "IN_PROGRESS", "DONE"}
    )
    private Status status;
    
    @Schema(
        description = "Task priority",
        example = "HIGH",
        allowableValues = {"LOW", "MEDIUM", "HIGH"}
    )
    private Priority priority;
    
    @Schema(
        description = "Assignee name",
        example = "DANIL-lab1"
    )
    private String assigneeName;
    
    @Schema(
        description = "Task deadline",
        example = "2026-12-31T23:59:59"
    )
    private LocalDateTime deadline;
    
    // Getters and Setters
    
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
}
