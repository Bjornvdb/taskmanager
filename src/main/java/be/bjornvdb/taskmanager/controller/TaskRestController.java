package be.bjornvdb.taskmanager.controller;


import be.bjornvdb.taskmanager.model.dto.TaskDTO;
import be.bjornvdb.taskmanager.model.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    Dit stond niet in de opdracht, maar dit is een voorbeeld
    van een restcontroller voor tasks.
*/

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDTO> getTasks() {
        return this.taskService.findAll();
    }

}
