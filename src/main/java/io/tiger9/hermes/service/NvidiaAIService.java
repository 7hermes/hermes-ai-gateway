package io.tiger9.hermes.service;


import io.tiger9.hermes.dto.AudioTranscriptionRequest;
import io.tiger9.hermes.dto.ChatCompletionRequest;
import io.tiger9.hermes.dto.ImageGenerationRequest;
import io.tiger9.hermes.dto.TextToSpeechRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.image.*;
import org.springframework.ai.openai.*;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Slf4j
@Service("nvidia")
public class NvidiaAIService implements AIService {

    private static final String NVIDIA_BASE_URL = "https://integrate.api.nvidia.com";

    public ChatResponse generateChatCompletion(
            String apiKey,
            ChatCompletionRequest request) {
        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model(request.model())
                .temperature(request.temperature())
                .maxTokens(request.maxTokens()).build();
        ChatModel chatModel = new OpenAiChatModel(new OpenAiApi(apiKey), options); //TODO Set other parameters
        ChatClient chatClient = ChatClient.create(chatModel);

        List<Message> messages =
        request.messages()
                        .stream()
                                .map(this::createMessage)
                                        .filter(Objects::nonNull)
                                                .toList();

        return chatClient.prompt().messages(messages).call().chatResponse();

    }



    @Override
    public ImageResponse generateImage(String apiKey, ImageGenerationRequest request) {



        ImageModel imageModel = new OpenAiImageModel(new OpenAiImageApi(apiKey));

        ImageOptions options = ImageOptionsBuilder.builder()
                .responseFormat(request.responseFormat())
                .model(request.model())
                .height(request.height())
                .N(request.n())
                .style(request.style())
                .width(request.width())
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(request.prompt(), options);
        return imageModel.call(imagePrompt);
    }


    @Override
    public AudioTranscriptionResponse transcribeAudio(String apiKey, AudioTranscriptionRequest request) {
        OpenAiAudioApi openAiAudioApi = new OpenAiAudioApi(apiKey);
        OpenAiAudioTranscriptionModel openAiTranscriptionModel = new OpenAiAudioTranscriptionModel(openAiAudioApi);

        OpenAiAudioTranscriptionOptions options = OpenAiAudioTranscriptionOptions.builder()
                .language(request.language())
                .prompt(request.prompt())
                .temperature(request.temperature())
                .responseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
                .build();


        AudioTranscriptionPrompt transcriptionRequest = new AudioTranscriptionPrompt(request.file().getResource(), options);
        return openAiTranscriptionModel.call(transcriptionRequest);
    }


    @Override
    public Resource generateSpeech(String apiKey, TextToSpeechRequest request) {
        OpenAiAudioApi openAiAudioApi = new OpenAiAudioApi(apiKey);

        OpenAiAudioSpeechModel openAiAudioSpeechModel = new OpenAiAudioSpeechModel(openAiAudioApi);

        var speechOptions = OpenAiAudioSpeechOptions.builder()
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .speed(request.speed())
                .model(OpenAiAudioApi.TtsModel.TTS_1.value)
                .build();

        SpeechPrompt speechPrompt = new SpeechPrompt("Hello, this is a text-to-speech example.", speechOptions);
        SpeechResponse response = openAiAudioSpeechModel.call(speechPrompt);

        byte[] responseAsBytes = response.getResult().getOutput();

        return new ByteArrayResource(responseAsBytes);
    }


} 
