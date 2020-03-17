package be.bjornvdb.taskmanager.service;

import be.bjornvdb.taskmanager.dto.TaskDTO;
import be.bjornvdb.taskmanager.model.SubTask;
import be.bjornvdb.taskmanager.model.Task;

import java.util.List;

public interface TaskServiceI {
    List<TaskDTO> findAll();
    TaskDTO findOne(long id);
    void add(TaskDTO taskDTO);
    void update(TaskDTO taskDTO);
    void createSubTask(long id, SubTask task);
}
