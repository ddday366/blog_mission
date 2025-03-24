package com.sprint.mission.blog_mission.dto.request;

public record UserCreateRequestDTO(
        String id,
        String password,
        String nickname,
        String email
) {
}
