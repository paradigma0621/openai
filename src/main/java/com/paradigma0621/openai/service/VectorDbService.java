package com.paradigma0621.openai.service;

import lombok.AllArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@AllArgsConstructor
@Service
public class VectorDbService {

    @Autowired
    private VectorStore vectorStore;

    public List<Document> searchJobs(String query) {
        return vectorStore.similaritySearch(query);
    }
    /*
    return vectorStore
            .similaritySearch(SearchRequest.query(query)
            .withTopK(3)); // Brings only 3 documents

     */
}
