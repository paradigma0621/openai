package com.paradigma0621.openai.service;

import lombok.AllArgsConstructor;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AudioService {

    private final OpenAiAudioTranscriptionModel openaiAudioTranscriptionModel;

    public String speechToText(String path) {
        OpenAiAudioTranscriptionOptions options = OpenAiAudioTranscriptionOptions
                .builder()
                .withLanguage("pt")
                .withResponseFormat(OpenAiAudioApi.TranscriptResponseFormat.VTT)
                .build();

        AudioTranscriptionPrompt transcriptionPrompt = new AudioTranscriptionPrompt(
                new FileSystemResource(path),
                options);

        return openaiAudioTranscriptionModel.call(transcriptionPrompt).getResult().getOutput();
    }

}
