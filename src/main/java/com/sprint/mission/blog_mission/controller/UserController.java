package com.sprint.mission.blog_mission.controller;

import com.sprint.mission.blog_mission.service.file.FileUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final FileUserService userService;

    public UserController(FileUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password) {
        return userService.login(id, password);
    }
}
