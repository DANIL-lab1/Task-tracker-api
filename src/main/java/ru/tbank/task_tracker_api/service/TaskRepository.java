package ru.tbank.task_tracker_api.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tbank.task_tracker_api.entity.Status;
import ru.tbank.task_tracker_api.entity.Task;

import java.util.List;
import java.util.UUID;

/**
 * Repository for {@link Task} entity.
 * <p>
 * Spring Data JPA automatically implements this interface
 * with CRUD operations.
 *
 * @author DANIL-lab1
 * @version 1.0
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, UUID>{
    
    /**
     * Finds all tasks with the given status.
     *
     * @param status the status to filter by
     * @return list of matching tasks
     */
    List<Task> findByStatus(Status status);
    
    /**
     * Finds all tasks assigned to the given person.
     *
     * @param assigneeName the assignee name
     * @return list of matching tasks
     */
    List<Task> findByAssigneeName(String assigneeName);
    
    /**
     * Finds all tasks with title containing the keyword (case-insensitive).
     *
     * @param keyword the search keyword
     * @return list of matching tasks
     */
    List<Task> findByTitleContainingIgnoreCase(String keyword);
}
