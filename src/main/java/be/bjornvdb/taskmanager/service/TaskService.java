package be.bjornvdb.taskmanager.service;


import be.bjornvdb.taskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements TaskServiceI {

    private List<Task> tasks;

    public TaskService() {
        this.tasks = new ArrayList<>();
        Task t1 = new Task(1, "Task 1", "Here is a description", LocalDateTime.now());
        Task t2 = new Task(2, "Task 2", "Here is a description", LocalDateTime.now());
        Task t3 = new Task(3,"Task 3", "Here is a description", LocalDateTime.now());
        this.tasks.add(t1);
        this.tasks.add(t2);
        this.tasks.add(t3);
    }

    @Override
    public List<Task> findAll() {
        return this.tasks;
    }

    @Override
    public Task findOne(long id) {
        try {
            return this.tasks.get((int) (id - 1));
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    @Override
    public void add(Task task) {
        task.setId(this.tasks.size() + 1);
        this.tasks.add(task);
    }

}
