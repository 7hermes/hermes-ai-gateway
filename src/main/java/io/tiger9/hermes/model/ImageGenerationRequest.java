package io.tiger9.hermes.model;

public record ImageGenerationRequest(
    String model,
    String prompt,
    Integer n,
    String size,
    String quality,
    String style,
    String response_format
) {} 