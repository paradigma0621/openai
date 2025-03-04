package com.paradigma0621.openai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.*;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FunctionCallingService {

    private final OpenAiChatModel chatModel;

    public String getStockPrice(String company) {

        var prompt = new Prompt("Get stock symbol and stock price for " + company,
                OpenAiChatOptions.builder().withFunction("stockRetrievalFunction").build());
        var response = chatModel.call(prompt);
        return response.getResult().getOutput().getContent();
    }
}