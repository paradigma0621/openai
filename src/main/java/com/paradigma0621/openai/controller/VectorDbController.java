package com.paradigma0621.openai.controller;

import com.paradigma0621.openai.service.VectorDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class VectorDbController {

    VectorDbService service;

    // Observation: requires to start ChromaDB
    //    docker run -it --rm --name chroma -p 8000:8000 ghcr.io/chroma-core/chroma:0.4.15
    // *IMPORTANT OBSERVATION*: For every new start of this microservice, stop and run again ChromaDB,
    //    otherwise, it will register multiple times the same documents
    @PostMapping("/jobSearchHelper")
    public String jobSearchHelper(@RequestBody String query) {
        var response = service.searchJobs(query);
        var result = new StringBuilder();
        response.forEach(doc -> result.append(doc.getContent()).append("\n\n"));
        return result.toString();
    }

    @PostMapping("/jobSearchHelperCounted")
    public String jobSearchHelperCounted(@RequestBody String query) {
        var response = service.searchJobsCounted(query);
        var result = new StringBuilder();
        response.forEach(doc -> result.append(doc.getContent()).append("\n\n"));
        return result.toString();
    }

}
