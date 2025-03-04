package com.paradigma0621.openai.controller;

import com.paradigma0621.openai.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ImageController {

	private final ImageService service;

	@PostMapping("/imageGenerator")
	public String imageGenerator(@RequestBody String prompt) {
		return service.generateImage(prompt);
	}

}