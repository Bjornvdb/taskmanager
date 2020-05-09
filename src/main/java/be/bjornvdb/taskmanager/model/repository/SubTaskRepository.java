package be.bjornvdb.taskmanager.model.repository;

import be.bjornvdb.taskmanager.model.entity.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
}
