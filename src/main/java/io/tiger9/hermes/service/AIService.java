package io.tiger9.hermes.service;

import io.tiger9.hermes.model.*;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface AIService {
    ChatResponse generateChatCompletion(String apiKey, ChatCompletionRequest request);
    //ImageGenerationResponse generateImage(ImageGenerationRequest request);
    //String transcribeAudio(AudioTranscriptionRequest request);
    //Resource generateSpeech(TextToSpeechRequest request);

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
