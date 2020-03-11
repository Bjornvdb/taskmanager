package be.bjornvdb.taskmanager.service;


import be.bjornvdb.taskmanager.model.SubTask;
import be.bjornvdb.taskmanager.model.Task;
import be.bjornvdb.taskmanager.repository.SubTaskRepository;
import be.bjornvdb.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements TaskServiceI {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubTaskRepository subTaskRepository;

    @Override
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public Task findOne(long id) {
        return this.taskRepository.findById(id).orElse(null);
    }

    @Override
    public void add(Task task) {
        this.taskRepository.save(task);
    }

    @Override
    public void update(Task task) {
        long id = task.getId();
        Task t = this.findOne(id);
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        t.setDate(task.getDate());
        this.taskRepository.save(t);
    }

    @Override
    public void createSubTask(long id, SubTask subTask) {
        Task task = this.findOne(id);
        subTask.setTask(task);
        this.subTaskRepository.save(subTask);
    }

}
