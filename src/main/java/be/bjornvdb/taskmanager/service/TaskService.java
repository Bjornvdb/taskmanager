package be.bjornvdb.taskmanager.service;


import be.bjornvdb.taskmanager.dto.SubTaskDTO;
import be.bjornvdb.taskmanager.dto.TaskDTO;
import be.bjornvdb.taskmanager.model.SubTask;
import be.bjornvdb.taskmanager.model.Task;
import be.bjornvdb.taskmanager.repository.SubTaskRepository;
import be.bjornvdb.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements TaskServiceI {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubTaskRepository subTaskRepository;

    @Override
    public List<TaskDTO> findAll() {
        return this.taskRepository.findAll().stream().map(t -> {
            TaskDTO dto = new TaskDTO();
            dto.setId(t.getId());
            dto.setTitle(t.getTitle());
            dto.setDescription(t.getDescription());
            dto.setDate(t.getDate());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public TaskDTO findOne(long id) {
        Task t = this.taskRepository.findById(id).orElse(null);
        if (t != null) {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(t.getId());
            taskDTO.setTitle(t.getTitle());
            taskDTO.setDescription(t.getDescription());
            taskDTO.setDate(t.getDate());
            taskDTO.setSubTasks(t.getSubTasks());
            return taskDTO;
        }
        return null;
    }

    @Override
    public void add(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDate(taskDTO.getDate());
        this.taskRepository.save(task);
    }

    @Override
    public void update(TaskDTO taskDTO) {
        Task t = this.taskRepository.findById(taskDTO.getId()).orElse(null);
        if (t != null) {
            t.setTitle(taskDTO.getTitle());
            t.setDescription(taskDTO.getDescription());
            t.setDate(taskDTO.getDate());
            this.taskRepository.save(t);
        }
    }

    @Override
    public void createSubTask(long id, SubTaskDTO subTask) {
        SubTask sub = new SubTask();
        sub.setTitle(subTask.getTitle());
        sub.setDescription(subTask.getDescription());
        Task t = this.taskRepository.findById(id).orElse(null);
        if (t != null) {
            sub.setTask(t);
            this.subTaskRepository.save(sub);
        }
    }

}
