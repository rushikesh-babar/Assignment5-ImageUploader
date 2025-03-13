package com.example.imageUploader.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.imageUploader.dao.ImageDao;

@Service
public class ImageService {
	
    private final String UPLOAD_DIR = "uploads/";
    
    private final ImageDao imageDao;
    public ImageService(ImageDao imageDao) {
    	this.imageDao=imageDao;
    }
    
    public String uploadImage(MultipartFile file) {
        try {
            if (file.getSize() > 2 * 1024 * 1024) {
                return "File size exceeds 2MB limit.";
            }

            String contentType = file.getContentType();
            if (!("image/jpeg".equals(contentType) || "image/png".equals(contentType))) {
                return "Only JPG and PNG formats are allowed.";
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            imageDao.saveImage(fileName);
            return "Image uploaded successfully: " + fileName;
        } catch (IOException e) {
            return "Error uploading image: " + e.getMessage();
        }
    }
    public List<com.example.imageUploader.model.Image> getAllImages() {
        return imageDao.getAllImages();
    }



}
