package com.paradigma0621.openai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.*;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FunctionCallingService {

    private final OpenAiChatModel chatModel;

    public String getStockPrice(String company) {

        Prompt prompt = new Prompt("Get stock symbol and stock price for " + company,
                OpenAiChatOptions.builder().withFunction("stockRetrievalFunction").build());
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getContent();
    }
}