package com.sprint.mission.blog_mission.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ImageService {
    List<Map<String, Object>> uploadImages(MultipartFile[] files);
    String getFileExtension(String filename);
}
