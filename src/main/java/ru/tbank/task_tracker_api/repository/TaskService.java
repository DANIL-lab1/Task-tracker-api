package ru.tbank.task_tracker_api.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.task_tracker_api.entity.Status;
import ru.tbank.task_tracker_api.entity.Task;
import ru.tbank.task_tracker_api.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

/**
 * Service layer for task management.
 *
 * @author DANIL-lab1
 * @version 1.0
 */
@Service
public class TaskService {
    
    private final TaskRepository repository;
    
    /**
     * Creates a new service with the given repository.
     *
     * @param repository the task repository
     */
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
    
    /**
     * Returns all tasks.
     *
     * @return list of all tasks
     */
    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return repository.findAll();
    }
    
    /**
     * Finds a task by ID.
     *
     * @param id the task ID
     * @return the task
     * @throws RuntimeException if not found
     */
    @Transactional(readOnly = true)
    public Task getTaskById(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found: " + id));
    }
    
    /**
     * Creates a new task.
     *
     * @param task the task to create
     * @return the created task
     */
    @Transactional
    public Task createTask(Task task) {
        return repository.save(task);
    }
    
    /**
     * Updates an existing task.
     *
     * @param id      the task ID
     * @param updated updated task data
     * @return the updated task
     */
    @Transactional
    public Task updateTask(UUID id, Task updated) {
        Task task = getTaskById(id);
        task.setTitle(updated.getTitle());
        task.setDescription(updated.getDescription());
        task.setStatus(updated.getStatus());
        task.setPriority(updated.getPriority());
        task.setAssigneeName(updated.getAssigneeName());
        task.setDeadline(updated.getDeadline());
        return repository.save(task);
    }
    
    /**
     * Deletes a task by ID.
     *
     * @param id the task ID
     */
    @Transactional
    public void deleteTask(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Task not found: " + id);
        }
        repository.deleteById(id);
    }
    
    /**
     * Finds tasks by status.
     *
     * @param status the status
     * @return list of matching tasks
     */
    @Transactional(readOnly = true)
    public List<Task> getTasksByStatus(Status status) {
        return repository.findByStatus(status);
    }
    
    /**
     * Searches tasks by title keyword.
     *
     * @param keyword the keyword
     * @return list of matching tasks
     */
    @Transactional(readOnly = true)
    public List<Task> searchByTitle(String keyword) {
        return repository.findByTitleContainingIgnoreCase(keyword);
    }
}
