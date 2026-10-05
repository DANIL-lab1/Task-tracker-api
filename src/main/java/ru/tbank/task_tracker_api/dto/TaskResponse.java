package ru.tbank.task_tracker_api.dto;

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
public class TaskResponse {
    
    private UUID id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String assigneeName;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public TaskResponse() {
    }
    
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
