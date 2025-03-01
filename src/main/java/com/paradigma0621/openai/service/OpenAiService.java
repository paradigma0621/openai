package com.paradigma0621.openai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Service
public class OpenAiService {

	private ChatClient chatClient;

	public OpenAiService(ChatClient.Builder builder) {
		chatClient = builder.defaultAdvisors(
				new MessageChatMemoryAdvisor(
						new InMemoryChatMemory())).build();
	}

	public ChatResponse generateAnswer(String question) {
		/*OpenAiChatOptions options = new OpenAiChatOptions();
		options.setModel("gpt-4o");
		options.setTemperature(1.0);
		options.setMaxTokens(4000);

		return chatClient.prompt(new Prompt(question, options)).call().chatResponse();
		*/

		return chatClient.prompt(question).call().chatResponse();
	}

	public String getTravelGuidance(String city, String month, String language, String budget) {
		String promptPattern =
				"""
				Welcome to the {city} travel guide!
				If you're visiting in {month}, here's what you can do: 
				1. Must-visit attractions.
				2. Local cuisine you must try. 
				3. Useful phrases in {language}.
				4. Tips for traveling on a {budget} budget." 
				Enjoy your trip!
				""";

		PromptTemplate promptTemplate = new PromptTemplate(promptPattern);

		Prompt prompt = promptTemplate
				.create(Map.of("city", city, "month", month, "language", language, "budget", budget));

		return chatClient.prompt(prompt).call().chatResponse().getResult().getOutput().getContent();
	}

}
