package com.paradigma0621.openai.controller;

import com.paradigma0621.openai.service.OpenAiService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class OpenAIController {

    private static final Logger logger = LoggerFactory.getLogger(OpenAIController.class);

    private OpenAiService service;

    @PostMapping("/askAnything")
    public String askAnything(@RequestBody String question) {
        ChatResponse response = service.generateAnswer(question);
        logger.info("Response:\n{}", response.getResult().getOutput().getContent());
        return response.getResult().getOutput().getContent();
    }

}
