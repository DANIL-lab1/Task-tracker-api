package ru.tbank.task_tracker_api.dto;

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
public class TaskRequest {
    
    @NotBlank(message = "Title cannot be empty")
    private String title;
    
    private String description;
    
    private Status status;
    
    private Priority priority;
    
    private String assigneeName;
    
    private LocalDateTime deadline;
    
    public TaskRequest() {
    }
    
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
