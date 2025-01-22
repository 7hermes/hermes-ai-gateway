package io.tiger9.hermes.service;

import io.tiger9.hermes.dto.AudioTranscriptionRequest;
import io.tiger9.hermes.dto.ChatCompletionRequest;
import io.tiger9.hermes.dto.ImageGenerationRequest;
import io.tiger9.hermes.dto.TextToSpeechRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service("qianfan")
public class QianFanService implements AIService {

    @Override
    public ChatResponse generateChatCompletion(String apiKey, ChatCompletionRequest request) {
        return null;
    }

    @Override
    public ImageResponse generateImage(String apiKey, ImageGenerationRequest request) {
        return null;
    }

    @Override
    public AudioTranscriptionResponse transcribeAudio(String apiKey, AudioTranscriptionRequest request) {
        return null;
    }

    @Override
    public Resource generateSpeech(String apiKey, TextToSpeechRequest request) {
        return null;
    }
}
