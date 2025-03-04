package com.paradigma0621.openai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final OpenAiImageModel openaiImageModel;

    public String generateImage(String prompt) {
        var response = openaiImageModel.call(new ImagePrompt(prompt,
                OpenAiImageOptions.builder()
                        .withQuality("hd")
                        .withHeight(1024)
                        .withWidth(1792)
                        .withN(1)  // Number of images to generate
                        .build()));

        return response.getResult().getOutput().getUrl();
    }
}
