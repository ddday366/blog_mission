package com.sprint.mission.blog_mission.controller;

import com.sprint.mission.blog_mission.dto.data.UserDTO;
import com.sprint.mission.blog_mission.dto.request.UserCreateRequestDTO;
import com.sprint.mission.blog_mission.entity.User;
import com.sprint.mission.blog_mission.service.file.FileUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final FileUserService userService;

    public UserController(FileUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserCreateRequestDTO request) {
        User user = userService.createUser(request);
        UserDTO userDTO = new UserDTO(user.getId(), user.getNickname(), user.getEmail());
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String id, @RequestParam String password) {
        String token = userService.login(id, password);
        return ResponseEntity.ok(token);
    }
}
