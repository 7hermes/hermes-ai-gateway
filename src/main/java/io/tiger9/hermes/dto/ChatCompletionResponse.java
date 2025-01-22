package io.tiger9.hermes.dto;

public record ChatCompletionResponse(
    String id,
    String content,
    String model,
    Usage usage,
    Long created
) {}