package com.sprint.mission.blog_mission.entity;

import com.sprint.mission.blog_mission.dto.request.UserCreateRequestDTO;

import java.time.Instant;
import java.util.UUID;

public class Image {
    private final UUID id;
    private String originalName;
    private String extension;
    private String path;
    private Long size;
    private Instant updatedAt;

    public Image() {
        this.id = UUID.randomUUID();
    }
}
