package com.sprint.mission.blog_mission.controller;

import com.sprint.mission.blog_mission.service.file.FileImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final FileImageService imageService;

    public ImageController(FileImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<Map<String, Object>>> uploadImages(@RequestParam("files") MultipartFile[] files) {
        List<Map<String, Object>> uploadedImages = imageService.uploadImages(files);
        return ResponseEntity.ok(uploadedImages);
    }
}
