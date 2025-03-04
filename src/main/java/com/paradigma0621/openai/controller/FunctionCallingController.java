package com.paradigma0621.openai.controller;

import com.paradigma0621.openai.service.FunctionCallingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FunctionCallingController {

	private final FunctionCallingService service;

	@GetMapping("/functionCalling")
	public String getChatResponse(@RequestParam("company") String company) {
		return service.getStockPrice(company);
	}

}
