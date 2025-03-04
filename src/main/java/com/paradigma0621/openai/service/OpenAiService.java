package com.paradigma0621.openai.service;

import com.paradigma0621.openai.dto.CountryCuisines;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class OpenAiService {

	private final ChatClient chatClient;

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
		var promptPattern =
				"""
				Welcome to the {city} travel guide!
				If you're visiting in {month}, here's what you can do: 
				1. Must-visit attractions.
				2. Local cuisine you must try. 
				3. Useful phrases in {language}.
				4. Tips for traveling on a {budget} budget." 
				Enjoy your trip!
				""";

		var promptTemplate = new PromptTemplate(promptPattern);

		var prompt = promptTemplate
				.create(Map.of("city", city, "month", month, "language", language, "budget", budget));

		return chatClient.prompt(prompt).call().chatResponse().getResult().getOutput().getContent();
	}

	public CountryCuisines getCuisines(String country, String numCuisines, String language) {

		var promptModel = """
			You are an expert in traditional cuisines.
			Answer the question: What is the traditional cuisine of {country}?
			Return a list of {numCuisines} in {language}.
			You provide information about a specific dish
			from a specific country.
			Avoid giving information about fictional places.
			If the country is fictional or non-existent
			return the country with out any cuisines.
			""";

		var promptTemplate = new PromptTemplate(promptModel);

		var prompt = promptTemplate
				.create(Map.of("country", country, "numCuisines", numCuisines, "language", language));

		return chatClient.prompt(prompt).call().entity(CountryCuisines.class); // Return the entity CountryCuisines, in
																				// witch field is a Collection
	}

	public Flux<String> streamAnswer(String question) {
		return chatClient.prompt(question).stream().content();
	}
}
