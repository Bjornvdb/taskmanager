package be.bjornvdb.taskmanager.model.repository;

import be.bjornvdb.taskmanager.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
