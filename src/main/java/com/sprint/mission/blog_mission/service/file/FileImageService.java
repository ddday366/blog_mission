package com.sprint.mission.blog_mission.service.file;

import com.sprint.mission.blog_mission.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FileImageService implements ImageService {
    private static final String UPLOAD_DIR = "uploads/images";
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif");
    private static final long MAX_FILE_SIZE = 1_048_576;

    @Override
    public List<Map<String, Object>> uploadImages(MultipartFile[] files) {
        List<Map<String, Object>> uploadImages = new ArrayList<>();

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;

            String extension = getFileExtension(originalFilename);

            if(!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())) {
                throw new IllegalArgumentException("허용되지 않은 파일 확장자입니다: " + extension);
            }

            if (file.getSize() > MAX_FILE_SIZE) {
                throw new IllegalArgumentException("파일 크가가 1MB를 초과할 수 없습니다.");
            }

            String uniqueFileName = UUID.randomUUID().toString() + "." + extension;
            String filepath = UPLOAD_DIR + File.separator + uniqueFileName;

            try {
                Files.write(Paths.get(filepath), file.getBytes());

                Map<String, Object> imageInfo = new HashMap<>();
                imageInfo.put("imageId", UUID.randomUUID().toString());
                imageInfo.put("originalName", originalFilename);
                imageInfo.put("path", filepath);
                imageInfo.put("size", file.getSize());

                uploadImages.add(imageInfo);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("파일 저장 실패: " + originalFilename);
            }
        }

        return uploadImages;
    }

    @Override
    public String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
