package com.sprint.mission.blog_mission.repository;

import com.sprint.mission.blog_mission.entity.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(String id);
    boolean existsById(String id);
}
