package com.paradigma0621.openai.controller;

import com.paradigma0621.openai.service.EmbeddingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@AllArgsConstructor
@RestController
public class EmbeddingController {

    private EmbeddingService service;

    @GetMapping("/embedding")
    public String embed(@RequestBody String text) { // 'text' can be any string (a single word or a sentence)
        float[] response = service.embed(text);
        return Arrays.toString(response);
    }
}