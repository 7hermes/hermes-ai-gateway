package io.tiger9.hermes.service;

import io.tiger9.hermes.model.*;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.core.io.Resource;

public interface AIService {
    ChatResponse generateChatCompletion(String apiKey, ChatCompletionRequest request);
    ImageResponse generateImage(String apiKey, ImageGenerationRequest request);
    AudioTranscriptionResponse transcribeAudio(String apiKey, AudioTranscriptionRequest request);
    Resource generateSpeech(String apiKey, TextToSpeechRequest request);

    default Message createMessage(PromptMessage message) {
        if("user".equals(message.role())){
            return new UserMessage(message.content());
        }
        else if("system".equals(message.role())){
            return new SystemMessage(message.content());
        }
        else{
            return null;
        }
    }
} 
