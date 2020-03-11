package be.bjornvdb.taskmanager.service;

import be.bjornvdb.taskmanager.model.SubTask;
import be.bjornvdb.taskmanager.model.Task;

import java.util.List;

public interface TaskServiceI {
    List<Task> findAll();
    Task findOne(long id);
    void add(Task task);
    void update(Task task);
    void createSubTask(long id, SubTask task);
}
