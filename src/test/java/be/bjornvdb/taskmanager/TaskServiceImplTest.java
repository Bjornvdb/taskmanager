package be.bjornvdb.taskmanager;

import be.bjornvdb.taskmanager.dto.SubTaskDTO;
import be.bjornvdb.taskmanager.dto.TaskDTO;
import be.bjornvdb.taskmanager.model.SubTask;
import be.bjornvdb.taskmanager.service.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceImplTest {

    @Autowired
    private TaskServiceImpl taskServiceImpl;


    @Test
    public void test_get_tasks() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Aan IPMINOR werken.");
        taskDTO.setDescription("Aan IPMINOR werken description.");
        taskDTO.setDate(LocalDateTime.of(2020, 3, 10, 10, 0));
        this.taskServiceImpl.add(taskDTO);

        List<TaskDTO> tasks = this.taskServiceImpl.findAll();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        TaskDTO task = tasks.get(0);
        assertNotNull(task);
    }

    @Test
    public void test_create_task() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Nog een leuke taak.");
        taskDTO.setDescription("Aan IPMINOR werken extra.");
        taskDTO.setDate(LocalDateTime.of(2010, 3, 20, 21, 50));

        this.taskServiceImpl.add(taskDTO);

        TaskDTO task = this.taskServiceImpl.findOne(2);

        assertNotNull(task);
        assertEquals(task.getDescription(), taskDTO.getDescription());
        assertEquals(task.getDate(), taskDTO.getDate());
    }

    @Test
    public void test_get_task() {
        TaskDTO task = this.taskServiceImpl.findOne(2);

        assertNotNull(task);
        assertEquals(task.getTitle(), "Nog een leuke taak.");
        assertEquals(task.getDescription(), "Aan IPMINOR werken extra.");
        assertEquals(task.getDate(), LocalDateTime.of(2010, 3, 20, 21, 50));
    }

    @Test
    public void test_update_task() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Nog een leuke taak.");
        taskDTO.setDescription("Aan IPMINOR werken extra.");
        taskDTO.setDate(LocalDateTime.of(2010, 3, 20, 21, 50));

        this.taskServiceImpl.add(taskDTO);

        TaskDTO task = this.taskServiceImpl.findOne(1);

        task.setTitle("Dit is een andere titel die update test.");
        task.setDescription("Dit is een andere description die update test.");
        task.setDate(LocalDateTime.of(2020, 4, 30, 10, 40));

        this.taskServiceImpl.update(task);

        TaskDTO updatedTask = this.taskServiceImpl.findOne(1);

        assertNotNull(updatedTask);
        assertEquals(updatedTask.getTitle(), "Dit is een andere titel die update test.");
        assertEquals(updatedTask.getDescription(), "Dit is een andere description die update test.");
        assertEquals(updatedTask.getDate(), LocalDateTime.of(2020, 4, 30, 10, 40));
    }

    @Test
    public void test_create_sub_task() {
        TaskDTO task = this.taskServiceImpl.findOne(2);

        SubTaskDTO subTaskDTO = new SubTaskDTO();

        subTaskDTO.setTitle("testing adding subtask title");
        subTaskDTO.setDescription("testing adding subtask description");

        this.taskServiceImpl.createSubTask(task.getId(), subTaskDTO);

        TaskDTO taskDTOWithSubTask = this.taskServiceImpl.findOne(2);

        List<SubTask> subTasks = taskDTOWithSubTask.getSubTasks();

        assertNotNull(subTasks);
        assertFalse(subTasks.isEmpty());
        assertEquals(subTasks.get(0).getTitle(), "testing adding subtask title");
        assertEquals(subTasks.get(0).getDescription(), "testing adding subtask description");
    }
}
