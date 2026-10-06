package ru.tbank.task_tracker_api.exception;

import java.util.UUID;

/**
 * Thrown when a task is not found by ID.
 *
 * @author DANIL-lab1
 * @version 1.0
 */
public class TaskNotFoundException extends RuntimeException {
    
    /**
     * Creates an exception for the given task ID.
     *
     * @param id the task ID
     */
    public TaskNotFoundException(UUID id) {
        super("Task not found: " + id);
    }
}
