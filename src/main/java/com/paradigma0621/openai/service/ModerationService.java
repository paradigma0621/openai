package com.paradigma0621.openai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.moderation.ModerationPrompt;
import org.springframework.ai.moderation.ModerationResult;
import org.springframework.ai.openai.OpenAiModerationModel;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ModerationService {

    private final OpenAiModerationModel moderationModel;

    public ModerationResult moderate(String text) {
        var moderation = moderationModel.call(new ModerationPrompt(text)).getResult().getOutput();
        return moderation.getResults().get(0);
    }

}
