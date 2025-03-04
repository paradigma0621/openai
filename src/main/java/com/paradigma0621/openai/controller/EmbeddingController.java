package com.paradigma0621.openai.controller;

import com.paradigma0621.openai.service.EmbeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequiredArgsConstructor
@RestController
public class EmbeddingController {

    private final EmbeddingService service;

    @PostMapping("/embedding")
    public String embed(@RequestBody String text) { // 'text' can be any string (a single word or a sentence)
        var response = service.embed(text);
        return Arrays.toString(response);
    }

    @GetMapping("/similarityFinder")
    public double findSimilarity(@RequestParam String text1, @RequestParam String text2) { // 'text1' and 'text2' can be
                                                                            // any string (a single word or a sentence)
        return service.findSimilarity(text1, text2);
    }
}