package io.tiger9.hermes.dto;

public record Usage(
    Integer promptTokens,
    Integer completionTokens,
    Integer totalTokens
) {} 