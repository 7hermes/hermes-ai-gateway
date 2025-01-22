package io.tiger9.hermes.dto;

import java.util.List;

public record ChatCompletionRequest(
    List<PromptMessage> messages,
    String model,
    Double temperature,
    Integer maxTokens
) {}
