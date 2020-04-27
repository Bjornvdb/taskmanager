package be.bjornvdb.taskmanager.repository;

import be.bjornvdb.taskmanager.model.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
}
