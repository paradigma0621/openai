package com.paradigma0621.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {

    @Bean
    public ChatClient chatClient(@Value("${spring.ai.openai.api-key}") String apiKey) {
        OpenAiApi openAiApi = new OpenAiApi(apiKey);
        OpenAiChatModel chatModel = new OpenAiChatModel(openAiApi);
        return ChatClient.create(chatModel);
    }
}
