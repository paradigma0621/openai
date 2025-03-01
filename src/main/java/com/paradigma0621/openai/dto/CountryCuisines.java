package com.paradigma0621.openai.dto;

import java.util.List;

public record CountryCuisines(String country, String numCuisines, List<String> cuisines) {

}