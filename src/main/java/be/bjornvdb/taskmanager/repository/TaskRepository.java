package be.bjornvdb.taskmanager.repository;

import be.bjornvdb.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
