package be.bjornvdb.taskmanager.model.repository;

import be.bjornvdb.taskmanager.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
