package com.paradigma0621.openai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final OpenAiImageModel openaiImageModel;

    private final ChatClient chatClient;

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

    public String explainImage(String prompt, String path) {
        return chatClient.prompt()
                .user(u -> u.text(prompt).media(MimeTypeUtils.IMAGE_JPEG, new FileSystemResource(path))).call()
                .content();
    }

    public String process2Images(String prompt, String path1, String path2) {
        String explanation = chatClient.prompt()
                .user(u -> u.text(prompt)
                        .media(MimeTypeUtils.IMAGE_JPEG, new FileSystemResource(path1))
                        .media(MimeTypeUtils.IMAGE_JPEG, new FileSystemResource(path2)))
                .call().content();
        return explanation;
    }
}
