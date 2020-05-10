package be.bjornvdb.taskmanager.controller;


import be.bjornvdb.taskmanager.model.dto.SubTaskDTO;
import be.bjornvdb.taskmanager.model.dto.TaskDTO;
import be.bjornvdb.taskmanager.model.entity.Task;
import be.bjornvdb.taskmanager.model.service.TaskService;
import be.bjornvdb.taskmanager.model.service.TaskServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService TaskService;

    public TaskController(TaskService TaskService) {
        this.TaskService = TaskService;
    }

    @GetMapping()
    public String getTasks(Model model) {
        model.addAttribute("tasks", this.TaskService.findAll());
        return "tasks";
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable long id, Model model) {
        model.addAttribute("task", this.TaskService.findOne(id));
        return "task";
    }

    @GetMapping("/new")
    public String getFormTask(Model model) {
        model.addAttribute("taskDTO", new TaskDTO());
        return "form";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "form";
        this.TaskService.add(taskDTO);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String updateTaskForm(@PathVariable long id, Model model) {
        model.addAttribute("taskDTO", this.TaskService.findOne(id));
        return "updateForm";
    }

    @PostMapping("/edit")
    public String updateTask(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "updateForm";
        this.TaskService.update(taskDTO);
        return "redirect:/tasks/" + taskDTO.getId();
    }

    @GetMapping("/{id}/sub/create")
    public String showCreateSubTask(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("subTaskDTO", new SubTaskDTO());
        return "subTaskForm";
    }

    @PostMapping("{id}/sub/create")
    public String createSubTask(@PathVariable long id, @ModelAttribute @Valid SubTaskDTO subTaskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "subTaskForm";
        this.TaskService.createSubTask(id, subTaskDTO);
        return "redirect:/tasks/" + id;
    }
}
