package io.tiger9.hermes.dto;

import org.springframework.web.multipart.MultipartFile;

public record AudioTranscriptionRequest(
    MultipartFile file,
    String model,
    String language,
    String prompt,
    String response_format,
    float temperature
) {} 