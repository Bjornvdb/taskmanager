package be.bjornvdb.taskmanager.model.service;

import be.bjornvdb.taskmanager.model.dto.CreateUserDTO;
import be.bjornvdb.taskmanager.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDTO createUser(CreateUserDTO createUserDTO);
}
