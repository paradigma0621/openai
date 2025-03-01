package com.paradigma0621.openai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

	private ChatClient chatClient;

	public OpenAiService(ChatClient.Builder builder) {
		chatClient = builder.build();
	}

	public ChatResponse generateAnswer(String question) {
		return chatClient.prompt(question).call().chatResponse();
	}
}
