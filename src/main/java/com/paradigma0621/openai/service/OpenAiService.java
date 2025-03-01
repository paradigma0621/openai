package com.paradigma0621.openai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

	private ChatClient chatClient;

	public OpenAiService(ChatClient.Builder builder) {
		chatClient = builder.defaultAdvisors(
				new MessageChatMemoryAdvisor(
						new InMemoryChatMemory())).build();
	}

	public ChatResponse generateAnswer(String question) {
		OpenAiChatOptions options = new OpenAiChatOptions();
		options.setModel("gpt-4o");
		options.setTemperature(1.0);
		options.setMaxTokens(4000);

		return chatClient.prompt(new Prompt(question, options)).call().chatResponse();
	}
}
