package com.paradigma0621.openai.controller;

import com.paradigma0621.openai.service.ModerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ModerationController {

    private final ModerationService service;

    @PostMapping("/moderation")
    public String moderation(@RequestBody String text) {
    	var moderationResult = service.moderate(text);
        return moderationResult.toString();
    }
}