package com.paradigma0621.openai.controller;

import com.paradigma0621.openai.dto.CountryCuisines;
import com.paradigma0621.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OpenAIController {

    private static final Logger logger = LoggerFactory.getLogger(OpenAIController.class);

    private final OpenAiService service;

    @PostMapping("/askAnything")
    public String askAnything(@RequestBody String question) {
        ChatResponse response = service.generateAnswer(question);
        logger.info("Response:\n{}", response.getResult().getOutput().getContent());
        return response.getResult().getOutput().getContent();
    }

    @GetMapping("/getTravelGuidance")
    public String askAnything(@RequestParam String city,
                              @RequestParam String month,
                              @RequestParam String language,
                              @RequestParam String budget) {
        return service.getTravelGuidance(city, month, language, budget);
    }


    @GetMapping("/cuisineHelper")
    public CountryCuisines getChatResponse(@RequestParam("country") String country,
                                           @RequestParam("numCuisines") String numCuisines,
                                           @RequestParam("language") String language) {
        return service.getCuisines(country, numCuisines, language);
    }

    @GetMapping("/stream")
    public Flux<String> streamAnswer(@RequestParam String question) {
        return service.streamAnswer(question);
    }

}
