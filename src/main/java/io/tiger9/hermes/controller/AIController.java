package io.tiger9.hermes.controller;

import io.tiger9.hermes.model.*;
import io.tiger9.hermes.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AIController {

    private final Map<String, AIService> aiServices;

    @PostMapping("/chat/completions")
    public ChatResponse createChatCompletion(
            @RequestBody ChatCompletionRequest request,
            @RequestHeader(value = "X-Provider") String provider,
            @RequestHeader(value = "X-API-Key") String apiKey) {
        
        AIService service = getService(provider);
        return service.generateChatCompletion(apiKey, request);
    }

    @PostMapping("/images/generations")
    public ImageGenerationResponse generateImage(
            @RequestBody ImageGenerationRequest request,
            @RequestHeader(value = "X-Provider", defaultValue = "openai") String provider,
            @RequestHeader(value = "X-API-Key") String apiKey) {
        
        AIService service = getService(provider);
        return null;
    }

    @PostMapping("/audio/transcriptions")
    public String transcribeAudio(
            @ModelAttribute AudioTranscriptionRequest request,
            @RequestHeader(value = "X-Provider", defaultValue = "openai") String provider,
            @RequestHeader(value = "X-API-Key") String apiKey) {
        
        AIService service = getService(provider);
        return null;
    }

    /*
    @PostMapping("/audio/speech")
    public ResponseEntity<Resource> generateSpeech(
            @RequestBody TextToSpeechRequest request,
            @RequestHeader(value = "X-Provider", defaultValue = "openai") String provider,
            @RequestHeader(value = "X-API-Key") String apiKey) {
        
        AIService service = getService(provider);
        Resource audioFile = service.generateSpeech(request);
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"speech.mp3\"")
                .body(audioFile);
    }

     */

    private AIService getService(String provider) {
        AIService service = aiServices.get(provider.toLowerCase());
        if (service == null) {
            throw new IllegalArgumentException("Unsupported provider: " + provider);
        }
        return service;
    }
} 
