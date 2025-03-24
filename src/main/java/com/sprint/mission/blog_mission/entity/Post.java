package com.sprint.mission.blog_mission.entity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Post {
    private final UUID id;
    private String title;
    private String content;
    private String authorId;
    private List<String> tags;
    private Instant createdAt;
    private Instant updatedAt;

    public Post() {
        this.id = UUID.randomUUID();
    }
}
