package com.paradigma0621.openai.controller;

import com.paradigma0621.openai.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@RequiredArgsConstructor
@RestController
public class ImageController {

	private static final String UPLOAD_DIR = "/home/paradigma0621/Documents/images/";

	private final ImageService service;

	@PostMapping("/imageGenerator")
	public String imageGenerator(@RequestBody String prompt) {
		return service.generateImage(prompt);
	}

	@PostMapping("/imageAnalyzer")
	public String uploadImage(String prompt,  // Given question about the image to be passed by parameter
							  @RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return "Please select a file to upload";
		}

		try {
			// Ensure the directory exists
			Path uploadDir = Paths.get(UPLOAD_DIR);
			if (Files.notExists(uploadDir)) {
				Files.createDirectories(uploadDir); // Create the directory if it doesn't exist
			}

			// Save the uploaded file to the specified directory
			Path path = uploadDir.resolve(file.getOriginalFilename());
			Files.write(path, file.getBytes(), StandardOpenOption.CREATE);

			// Generate explanation and add to the model
			return service.explainImage(prompt,path.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return "Failed to upload file. " + e.getMessage();
		}
	}

}