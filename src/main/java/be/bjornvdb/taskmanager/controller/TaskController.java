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
    public String updateTask(@ModelAttribute Task task) {
        this.taskService.update(task);
        return "redirect:/tasks/" + task.getId();
    }

    @GetMapping("/{id}/sub/create")
    public String showCreateSubTask(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        return "subTaskForm";
    }

    @PostMapping("{id}/sub/create")
    public String createSubTask(@PathVariable long id, @ModelAttribute Task task) {
        this.taskService.createSubTask(id, task);
        return "redirect:/tasks/" + id;
    }


}
