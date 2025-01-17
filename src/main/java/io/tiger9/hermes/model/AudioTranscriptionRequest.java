package io.tiger9.hermes.model;

import org.springframework.web.multipart.MultipartFile;

public record AudioTranscriptionRequest(
    MultipartFile file,
    String model,
    String language,
    String prompt,
    String response_format,
    Double temperature
) {} 