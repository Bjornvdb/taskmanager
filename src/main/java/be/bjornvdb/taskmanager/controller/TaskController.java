package be.bjornvdb.taskmanager.controller;


import be.bjornvdb.taskmanager.dto.SubTaskDTO;
import be.bjornvdb.taskmanager.dto.TaskDTO;
import be.bjornvdb.taskmanager.model.SubTask;
import be.bjornvdb.taskmanager.model.Task;
import be.bjornvdb.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public String getTasks(Model model) {
        model.addAttribute("tasks", this.taskService.findAll());
        return "tasks";
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable long id, Model model) {
        model.addAttribute("task", this.taskService.findOne(id));
        return "task";
    }

    @GetMapping("/new")
    public String getFormTask(Model model) {
        model.addAttribute("taskDTO", new TaskDTO());
        return "form";
    }

    @PostMapping()
    public String createTask(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "form";
        this.taskService.add(taskDTO);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String updateTaskForm(@PathVariable long id, Model model) {
        model.addAttribute("taskDTO", this.taskService.findOne(id));
        return "updateForm";
    }

    @PostMapping("/edit")
    public String updateTask(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "updateForm";
        this.taskService.update(taskDTO);
        return "redirect:/tasks/" + taskDTO.getId();
    }

    @GetMapping("/{id}/sub/create")
    public String showCreateSubTask(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("subTaskDTO", new SubTaskDTO());
        return "subTaskForm";
    }

    @PostMapping("{id}/sub/create")
    public String createSubTask(@PathVariable long id, @ModelAttribute @Valid SubTask subTaskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "subTaskForm";
        this.taskService.createSubTask(id, subTaskDTO);
        return "redirect:/tasks/" + id;
    }
}
