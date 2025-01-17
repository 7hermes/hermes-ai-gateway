package io.tiger9.hermes.service;


import io.tiger9.hermes.model.ChatCompletionRequest;
import io.tiger9.hermes.model.PromptMessage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service("openai")
public class OpenAIService implements AIService {


    public ChatResponse generateChatCompletion(
            String apiKey,
            ChatCompletionRequest request) {
        ChatModel chatModel = new OpenAiChatModel(new OpenAiApi(apiKey)); //TODO Set other parameters
        ChatClient chatClient = ChatClient.create(chatModel);

        List<Message> messages =
        request.messages()
                        .stream()
                                .map(this::createMessage)
                                        .filter(Objects::nonNull)
                                                .toList();

        return chatClient.prompt().messages(messages).call().chatResponse();

    }



    /*
    @Override
    public ImageGenerationResponse generateImage(ImageGenerationRequest request) {
        List<Image> images = imageClient.generateImage(request.getPrompt(), request.getN());
        
        ImageGenerationResponse response = new ImageGenerationResponse();
        response.setCreated(System.currentTimeMillis() / 1000);
        response.setData(images.stream()
                .map(img -> {
                    ImageGenerationResponse.Image image = new ImageGenerationResponse.Image();
                    image.setUrl(img.getUrl());
                    return image;
                })
                .collect(Collectors.toList()));
        
        return response;
    }

    @Override
    public String transcribeAudio(AudioTranscriptionRequest request) {
        return audioClient.transcribe(request.getFile().getResource());
    }

    @Override
    public Resource generateSpeech(TextToSpeechRequest request) {
        return audioClient.speech(request.getInput(), request.getVoice());
    }

    @Override
    public String getProvider() {
        return "openai";
    }

     */
} 
