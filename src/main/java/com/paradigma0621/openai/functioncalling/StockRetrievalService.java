package com.paradigma0621.openai.functioncalling;

import java.util.function.Function;


public class StockRetrievalService implements Function<StockRetrievalService.Request, StockRetrievalService.Response> {
	
	public record Request(String symbol) {
		
	}
	
	public record Response(Double price) {
		
	}

	@Override
	public Response apply(Request request) {
		return new Response(5000D); // Could be a request to an API to get the stock price
	}

}
