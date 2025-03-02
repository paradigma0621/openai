package com.paradigma0621.openai;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class DataInitializer {

	private final VectorStore vectorStore;
	
	@PostConstruct
	public void init() {
		var jobListReader = new TextReader(new ClassPathResource("job_listings.txt"));
		var tokenTextSplitter = new TokenTextSplitter(100,100,5,1000,true);
		var documents = tokenTextSplitter.split(jobListReader.get());
		vectorStore.add(documents);

		var productDataReader = new TextReader(new ClassPathResource("product-data.txt"));
		documents = tokenTextSplitter.split(productDataReader.get());
		vectorStore.add(documents);
	}

}
