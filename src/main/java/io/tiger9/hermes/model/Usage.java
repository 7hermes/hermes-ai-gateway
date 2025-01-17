package io.tiger9.hermes.model;

public record Usage(
    Integer promptTokens,
    Integer completionTokens,
    Integer totalTokens
) {} 