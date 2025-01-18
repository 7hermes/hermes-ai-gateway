package io.tiger9.hermes.controller;

import io.tiger9.hermes.model.*;
import io.tiger9.hermes.service.AIService;
import io.tiger9.hermes.service.AIServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AIController {

    private final AIServiceFactory aiServiceFactory;

    @PostMapping("/chat/completions")
    public ChatResponse createChatCompletion(
            @RequestBody ChatCompletionRequest request,
            @RequestHeader(value = "X-Provider") String provider,
            @RequestHeader(value = "X-API-Key") String apiKey) {

        AIService service = aiServiceFactory.getService(provider);
        return service.generateChatCompletion(apiKey, request);
    }

    @PostMapping("/images/generations")
    public ImageResponse generateImage(
            @RequestBody ImageGenerationRequest request,
            @RequestHeader(value = "X-Provider") String provider,
            @RequestHeader(value = "X-API-Key") String apiKey) {

        AIService service = aiServiceFactory.getService(provider);
        return service.generateImage(apiKey, request);
    }

    @PostMapping("/audio/transcriptions")
    public AudioTranscriptionResponse transcribeAudio(
            @ModelAttribute AudioTranscriptionRequest request,
            @RequestHeader(value = "X-Provider") String provider,
            @RequestHeader(value = "X-API-Key") String apiKey) {

        AIService service = aiServiceFactory.getService(provider);
        return service.transcribeAudio(apiKey, request);
    }


    @PostMapping("/audio/speech")
    public ResponseEntity<Resource> generateSpeech(
            @RequestBody TextToSpeechRequest request,
            @RequestHeader(value = "X-Provider") String provider,
            @RequestHeader(value = "X-API-Key") String apiKey) {

        AIService service = aiServiceFactory.getService(provider);
        Resource audioFile = service.generateSpeech(apiKey, request);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"speech.mp3\"")
                .body(audioFile);
    }



}
