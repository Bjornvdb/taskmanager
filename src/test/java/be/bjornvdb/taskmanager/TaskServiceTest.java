package be.bjornvdb.taskmanager;

import be.bjornvdb.taskmanager.dto.TaskDTO;
import be.bjornvdb.taskmanager.model.Task;
import be.bjornvdb.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void test() {
        assertNull(null);
    }

    @Test
    public void test_get_tasks() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Aan IPMINOR werken.");
        taskDTO.setDescription("Aan IPMINOR werken.");
        taskDTO.setDate(LocalDateTime.of(2020, 3, 10, 10, 0));
        taskService.add(taskDTO);

        List<TaskDTO> tasks = taskService.findAll();

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

        taskService.add(taskDTO);

        TaskDTO task = taskService.findOne(1);

        assertNotNull(task);
        assertEquals(task.getDescription(), taskDTO.getDescription());
        assertEquals(task.getDate(), taskDTO.getDate());
    }
}
