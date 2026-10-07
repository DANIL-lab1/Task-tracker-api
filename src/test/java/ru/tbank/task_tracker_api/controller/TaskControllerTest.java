package ru.tbank.task_tracker_api.controller;

import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.tbank.task_tracker_api.dto.TaskRequest;
import ru.tbank.task_tracker_api.entity.Priority;
import ru.tbank.task_tracker_api.entity.Status;
import ru.tbank.task_tracker_api.entity.Task;
import ru.tbank.task_tracker_api.exception.TaskNotFoundException;
import ru.tbank.task_tracker_api.service.TaskService;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for {@link TaskController}.
 * <p>
 * Uses MockMvc to test HTTP layer without starting a real server.
 *
 * @author DANIL-lab1
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") 
class TaskControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockitoBean
    private TaskService service;
    
    // ========== GET /api/tasks ==========
    
    @Test
    @DisplayName("GET /api/tasks returns 200 with list of tasks")
    void getAllTasks_ReturnsOkWithTasks() throws Exception {
        // Arrange
        Task task1 = new Task("Task 1", "Desc 1");
        Task task2 = new Task("Task 2", "Desc 2");
        when(service.getAllTasks()).thenReturn(List.of(task1, task2));
        
        // Act & Assert
        mockMvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].title").value("Task 1"))
            .andExpect(jsonPath("$[1].title").value("Task 2"));
        
        verify(service).getAllTasks();
    }
    
    // ========== GET /api/tasks/{id} ==========
    
    @Test
    @DisplayName("GET /api/tasks/{id} returns 200 with task")
    void getTaskById_ReturnsOkWithTask() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        Task task = new Task("Test", "Desc");
        task.setId(id);
        when(service.getTaskById(id)).thenReturn(task);
        
        // Act & Assert
        mockMvc.perform(get("/api/tasks/{id}", id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id.toString()))
            .andExpect(jsonPath("$.title").value("Test"));
        
        verify(service).getTaskById(id);
    }
    
    @Test
    @DisplayName("GET /api/tasks/{id} returns 404 when not found")
    void getTaskById_NotFound_Returns404() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        when(service.getTaskById(id)).thenThrow(new TaskNotFoundException(id));
        
        // Act & Assert
        mockMvc.perform(get("/api/tasks/{id}", id))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.error").value("Not Found"))
            .andExpect(jsonPath("$.message").value("Task not found: " + id));
    }
    
    // ========== POST /api/tasks ==========
    
    @Test
    @DisplayName("POST /api/tasks creates task and returns 201")
    void createTask_ValidRequest_ReturnsCreated() throws Exception {
        // Arrange
        TaskRequest request = new TaskRequest();
        request.setTitle("New task");
        request.setDescription("Description");
        request.setPriority(Priority.HIGH);
        
        Task created = new Task("New task", "Description");
        created.setPriority(Priority.HIGH);
        when(service.createTask(any(Task.class))).thenReturn(created);
        
        // Act & Assert
        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value("New task"))
            .andExpect(jsonPath("$.priority").value("HIGH"));
        
        verify(service).createTask(any(Task.class));
    }
    
    @Test
    @DisplayName("POST /api/tasks returns 400 when title is blank")
    void createTask_BlankTitle_Returns400() throws Exception {
        // Arrange
        TaskRequest request = new TaskRequest();
        request.setTitle("");  // Invalid
        request.setDescription("Description");
        
        // Act & Assert
        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.validationErrors.title").exists());
        
        verify(service, never()).createTask(any(Task.class));
    }
    
    // ========== PUT /api/tasks/{id} ==========
    
    @Test
    @DisplayName("PUT /api/tasks/{id} updates task and returns 200")
    void updateTask_ReturnsUpdatedTask() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        TaskRequest request = new TaskRequest();
        request.setTitle("Updated title");
        request.setStatus(Status.DONE);
        
        Task updated = new Task("Updated title", "Desc");
        updated.setId(id);
        updated.setStatus(Status.DONE);
        
        when(service.updateTask(eq(id), any(Task.class))).thenReturn(updated);
        
        // Act & Assert
        mockMvc.perform(put("/api/tasks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Updated title"))
            .andExpect(jsonPath("$.status").value("DONE"));
    }
    
    // ========== DELETE /api/tasks/{id} ==========
    
    @Test
    @DisplayName("DELETE /api/tasks/{id} returns 204")
    void deleteTask_Returns204() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(service).deleteTask(id);
        
        // Act & Assert
        mockMvc.perform(delete("/api/tasks/{id}", id))
            .andExpect(status().isNoContent());
        
        verify(service).deleteTask(id);
    }
    
    @Test
    @DisplayName("DELETE /api/tasks/{id} returns 404 when not found")
    void deleteTask_NotFound_Returns404() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        doThrow(new TaskNotFoundException(id)).when(service).deleteTask(id);
        
        // Act & Assert
        mockMvc.perform(delete("/api/tasks/{id}", id))
            .andExpect(status().isNotFound());
    }
    
    // ========== GET /api/tasks/filter ==========
    
    @Test
    @DisplayName("GET /api/tasks/filter returns tasks by status")
    void filterByStatus_ReturnsFilteredTasks() throws Exception {
        // Arrange
        Task task = new Task("Done task", "Desc");
        task.setStatus(Status.DONE);
        when(service.getTasksByStatus(Status.DONE)).thenReturn(List.of(task));
        
        // Act & Assert
        mockMvc.perform(get("/api/tasks/filter").param("status", "DONE"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].status").value("DONE"));
    }
    
    // ========== GET /api/tasks/search ==========
    
    @Test
    @DisplayName("GET /api/tasks/search returns matching tasks")
    void search_ReturnsMatchingTasks() throws Exception {
        // Arrange
        Task task = new Task("Java task", "Desc");
        when(service.searchByTitle("Java")).thenReturn(List.of(task));
        
        // Act & Assert
        mockMvc.perform(get("/api/tasks/search").param("keyword", "Java"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].title").value("Java task"));
    }
}