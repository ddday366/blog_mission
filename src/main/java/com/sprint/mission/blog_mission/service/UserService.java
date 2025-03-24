package com.sprint.mission.blog_mission.service;

import com.sprint.mission.blog_mission.dto.request.UserCreateRequestDTO;
import com.sprint.mission.blog_mission.entity.User;

public interface UserService {
    User createUser(UserCreateRequestDTO userCreateRequestDTO);
}
