package be.bjornvdb.taskmanager.model.service;


import be.bjornvdb.taskmanager.model.dto.SubTaskDTO;
import be.bjornvdb.taskmanager.model.dto.TaskDTO;
import be.bjornvdb.taskmanager.model.entity.SubTask;
import be.bjornvdb.taskmanager.model.entity.Task;
import be.bjornvdb.taskmanager.model.repository.SubTaskRepository;
import be.bjornvdb.taskmanager.model.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final SubTaskRepository subTaskRepository;

    public TaskServiceImpl(TaskRepository taskRepository, SubTaskRepository subTaskRepository) {
        this.taskRepository = taskRepository;
        this.subTaskRepository = subTaskRepository;
    }

    @Override
    public List<TaskDTO> findAll() {
        return this.taskRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public TaskDTO findOne(long id) {
        Task t = this.taskRepository.findById(id).orElse(null);
        if (t != null) {
            return this.convertToDTO(t);
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

    private TaskDTO convertToDTO(Task t) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(t.getId());
        taskDTO.setTitle(t.getTitle());
        taskDTO.setDescription(t.getDescription());
        taskDTO.setDate(t.getDate());
        taskDTO.setSubTasks(t.getSubTasks());
        return taskDTO;
    }
}
