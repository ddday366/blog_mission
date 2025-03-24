package com.sprint.mission.blog_mission.entity;

import java.util.UUID;

public class PostImage {
    private final UUID id;
    private final UUID postId;
    private final UUID imageId;

    public PostImage(UUID id, UUID postId, UUID imageId) {
        this.id = UUID.randomUUID();
        this.postId = postId;
        this.imageId = imageId;
    }
}
