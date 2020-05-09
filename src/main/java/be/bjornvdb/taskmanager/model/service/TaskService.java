package be.bjornvdb.taskmanager.model.service;

import be.bjornvdb.taskmanager.model.dto.SubTaskDTO;
import be.bjornvdb.taskmanager.model.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> findAll();
    TaskDTO findOne(long id);
    void add(TaskDTO taskDTO);
    void update(TaskDTO taskDTO);
    void createSubTask(long id, SubTaskDTO task);
}
