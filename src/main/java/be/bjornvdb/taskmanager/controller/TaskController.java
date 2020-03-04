package be.bjornvdb.taskmanager.controller;


import be.bjornvdb.taskmanager.model.Task;
import be.bjornvdb.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public String getTasks(Model model) {
        model.addAttribute("tasks", this.taskService.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable long id, Model model) {
        model.addAttribute("task", this.taskService.findOne(id));
        return "task";
    }

    @GetMapping("/new")
    public String getFormTask() {
        return "form";
    }

    @PostMapping()
    public String createTask(@ModelAttribute Task task) {
        this.taskService.add(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String updateTaskForm(@PathVariable long id, Model model) {
        model.addAttribute("task", this.taskService.findOne(id));
        return "updateForm";
    }

    @PostMapping("/edit")
    public String updateTask(@PathVariable long id, @ModelAttribute Task task) {
        Task t1 = this.taskService.findOne(id);
        t1.setTitle(task.getTitle());
        t1.setDescription(task.getDescription());
        t1.setDate(task.getDate());
        return "redirect:/tasks/";
    }

}
