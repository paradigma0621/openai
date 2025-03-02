package com.paradigma0621.openai.controller;

import com.paradigma0621.openai.service.AudioService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@AllArgsConstructor
@RestController
public class AudioController {

    private final AudioService service;

    // Define the folder where audios will be saved
    private static final String UPLOAD_DIR = "/home/paradigma0621/Documents/audios/";

    @PostMapping("/speechToText")
    public String speechToText(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty())
            return "Please select a file to upload";

        try {
            // Ensure the directory exists
            var uploadDir = Paths.get(UPLOAD_DIR);
            if (Files.notExists(uploadDir)) {
                Files.createDirectories(uploadDir); // Create the directory if it doesn't exist
            }

            // Save the uploaded file to the specified directory
            var path = uploadDir.resolve(file.getOriginalFilename());
            Files.write(path, file.getBytes(), StandardOpenOption.CREATE);
            // Generate explanation
            return service.speechToText(path.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file - due to: " + e.getMessage();
        }
    }

}
