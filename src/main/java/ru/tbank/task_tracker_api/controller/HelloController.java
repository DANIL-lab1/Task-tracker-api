package ru.tbank.task_tracker_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test controller to verify the application works.
 *
 * @author DANIL-lab1
 * @version 1.0
 */
@RestController
public class HelloController {
    
    /**
     * Test endpoint.
     *
     * @return greeting message
     */
    @GetMapping("/hello")
    public String hello(){
        return "Hello, TaskTracker API!";
    }
}
