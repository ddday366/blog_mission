package com.sprint.mission.blog_mission.entity;

import com.sprint.mission.blog_mission.dto.request.UserCreateRequestDTO;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String password;
    private String email;
    private String nickname;
    private Instant createdAt;

    public User(UserCreateRequestDTO dto) {
        this.id = dto.id();
        this.password = dto.password();
        this.nickname = dto.nickname();
        this.email = dto.email();
    }

    public User(String id, String password, String nickname, String email) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
}
