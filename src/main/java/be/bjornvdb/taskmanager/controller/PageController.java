package be.bjornvdb.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping
    public String getIndex() {
        return "redirect:/tasks";
    }

    @RequestMapping(value = "/403", method = { RequestMethod.GET, RequestMethod.POST })
    public String get403() {
        return "403";
    }

}
