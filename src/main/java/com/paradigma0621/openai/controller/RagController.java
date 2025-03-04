package com.paradigma0621.openai.controller;

import com.paradigma0621.openai.service.VectorDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RagController {

    private final VectorDbService service;

    @PostMapping("/productDataBot")
    public String productDataBot(@RequestBody String query) {
        return service.answer(query);
    }
}
