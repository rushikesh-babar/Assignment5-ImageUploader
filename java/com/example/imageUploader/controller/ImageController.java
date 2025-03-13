package com.example.imageUploader.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.imageUploader.model.Image;
import com.example.imageUploader.service.ImageService;

@RestController
@RequestMapping("/api/images")
public class ImageController {
	
	private final ImageService imageService;
	 public ImageController(ImageService imageService) {
	        this.imageService = imageService;
	    }
	 
	 @PostMapping("/upload")
	    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
	        String response = imageService.uploadImage(file);
	        return response.startsWith("Image uploaded") ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
	    }

	    @GetMapping
	    public ResponseEntity<List<Image>> getAllImages() {
	        return ResponseEntity.ok(imageService.getAllImages());
	    }

}
