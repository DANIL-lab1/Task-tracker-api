package ru.tbank.task_tracker_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tbank.task_tracker_api.entity.Status;
import ru.tbank.task_tracker_api.entity.Task;
import ru.tbank.task_tracker_api.exception.TaskNotFoundException;
import ru.tbank.task_tracker_api.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link TaskService}.
 * <p>
 * Uses Mockito to mock the repository.
 *
 * @author DANIL-lab1
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    
    @Mock
    private TaskRepository repository;
    
    @InjectMocks
    private TaskService service;
    
    private Task task;
    private UUID taskId;
    
    @BeforeEach
    void setUp() {
        taskId = UUID.randomUUID();
        task = new Task("Test task", "Test description");
        task.setId(taskId);
    }
    
    // ========== getAllTasks ==========
    
    @Test
    @DisplayName("getAllTasks returns all tasks")
    void getAllTasks_ReturnsAllTasks() {
        // Arrange
        Task task2 = new Task("Second", "Desc");
        when(repository.findAll()).thenReturn(List.of(task, task2));
        
        // Act
        List<Task> result = service.getAllTasks();
        
        // Assert
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(task, task2);
        verify(repository, times(1)).findAll();
    }
    
    @Test
    @DisplayName("getAllTasks returns empty list when no tasks")
    void getAllTasks_NoTasks_ReturnsEmpty() {
        when(repository.findAll()).thenReturn(List.of());
        
        List<Task> result = service.getAllTasks();
        
        assertThat(result).isEmpty();
    }
    
    // ========== getTaskById ==========
    
    @Test
    @DisplayName("getTaskById returns task when found")
    void getTaskById_TaskExists_ReturnsTask() {
        // Arrange
        when(repository.findById(taskId)).thenReturn(Optional.of(task));
        
        // Act
        Task result = service.getTaskById(taskId);
        
        // Assert
        assertThat(result).isEqualTo(task);
        assertThat(result.getTitle()).isEqualTo("Test task");
        verify(repository).findById(taskId);
    }
    
    @Test
    @DisplayName("getTaskById throws TaskNotFoundException when not found")
    void getTaskById_TaskNotFound_ThrowsException() {
        // Arrange
        when(repository.findById(taskId)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThatThrownBy(() -> service.getTaskById(taskId))
            .isInstanceOf(TaskNotFoundException.class)
            .hasMessageContaining(taskId.toString());
        
        verify(repository).findById(taskId);
    }
    
    // ========== createTask ==========
    
    @Test
    @DisplayName("createTask saves and returns task")
    void createTask_SavesAndReturnsTask() {
        // Arrange
        when(repository.save(any(Task.class))).thenReturn(task);
        
        // Act
        Task result = service.createTask(task);
        
        // Assert
        assertThat(result).isEqualTo(task);
        verify(repository).save(task);
    }
    
    // ========== updateTask ==========
    
    @Test
    @DisplayName("updateTask updates existing task fields")
    void updateTask_ExistingTask_UpdatesFields() {
        // Arrange
        Task updated = new Task("Updated title", "Updated desc");
        updated.setStatus(Status.DONE);
        
        when(repository.findById(taskId)).thenReturn(Optional.of(task));
        when(repository.save(any(Task.class))).thenAnswer(inv -> inv.getArgument(0));
        
        // Act
        Task result = service.updateTask(taskId, updated);
        
        // Assert
        assertThat(result.getTitle()).isEqualTo("Updated title");
        assertThat(result.getDescription()).isEqualTo("Updated desc");
        assertThat(result.getStatus()).isEqualTo(Status.DONE);
        verify(repository).findById(taskId);
        verify(repository).save(task);
    }
    
    @Test
    @DisplayName("updateTask throws exception when task not found")
    void updateTask_TaskNotFound_ThrowsException() {
        // Arrange
        when(repository.findById(taskId)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThatThrownBy(() -> service.updateTask(taskId, task))
            .isInstanceOf(TaskNotFoundException.class);
        
        verify(repository, never()).save(any(Task.class));
    }
    
    // ========== deleteTask ==========
    
    @Test
    @DisplayName("deleteTask deletes existing task")
    void deleteTask_ExistingTask_DeletesTask() {
        // Arrange
        when(repository.existsById(taskId)).thenReturn(true);
        
        // Act
        service.deleteTask(taskId);
        
        // Assert
        verify(repository).existsById(taskId);
        verify(repository).deleteById(taskId);
    }
    
    @Test
    @DisplayName("deleteTask throws exception when task not found")
    void deleteTask_TaskNotFound_ThrowsException() {
        // Arrange
        when(repository.existsById(taskId)).thenReturn(false);
        
        // Act & Assert
        assertThatThrownBy(() -> service.deleteTask(taskId))
            .isInstanceOf(TaskNotFoundException.class);
        
        verify(repository, never()).deleteById(any());
    }
    
    // ========== getTasksByStatus ==========
    
    @Test
    @DisplayName("getTasksByStatus returns tasks with given status")
    void getTasksByStatus_ReturnsFilteredTasks() {
        // Arrange
        task.setStatus(Status.DONE);
        when(repository.findByStatus(Status.DONE)).thenReturn(List.of(task));
        
        // Act
        List<Task> result = service.getTasksByStatus(Status.DONE);
        
        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStatus()).isEqualTo(Status.DONE);
        verify(repository).findByStatus(Status.DONE);
    }
    
    // ========== searchByTitle ==========
    
    @Test
    @DisplayName("searchByTitle returns matching tasks")
    void searchByTitle_ReturnsMatchingTasks() {
        // Arrange
        when(repository.findByTitleContainingIgnoreCase("Test"))
            .thenReturn(List.of(task));
        
        // Act
        List<Task> result = service.searchByTitle("Test");
        
        // Assert
        assertThat(result).hasSize(1);
        verify(repository).findByTitleContainingIgnoreCase("Test");
    }
}