package be.bjornvdb.taskmanager.controller;

import be.bjornvdb.taskmanager.model.dto.CreateUserDTO;
import be.bjornvdb.taskmanager.model.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "login-form";
    }

    @GetMapping("/register")
    public String getCreateUser(Model model) {
        model.addAttribute("user", new CreateUserDTO());
        return "register-form";
    }

    @PostMapping("/register")
    public String postCreateUser(@ModelAttribute("user") @Valid CreateUserDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register-form";
        }
        this.userService.createUser(user);
        return "redirect:/login";
    }


}
