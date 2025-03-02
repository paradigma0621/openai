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
       /*  Transcribes the given audio file without translation into a specific language
           and without including time references in the output.
        var transcriptionPrompt = new AudioTranscriptionPrompt(new FileSystemResource(path));
        return openaiAudioTranscriptionModel.call(transcriptionPrompt).getResult().getOutput();  */

        /* Transcribes the given audio file while translating it into a specific language
           (in this case, French) and includes time references using the VTT (WebVTT) format. */
        var options = OpenAiAudioTranscriptionOptions
                .builder()
                .withLanguage("fr")
                .withResponseFormat(OpenAiAudioApi.TranscriptResponseFormat.VTT)
                .build();

        var transcriptionPrompt = new AudioTranscriptionPrompt(new FileSystemResource(path), options);

        return openaiAudioTranscriptionModel.call(transcriptionPrompt).getResult().getOutput();
    }

}
