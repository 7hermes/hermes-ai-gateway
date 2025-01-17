package io.tiger9.hermes.model;

import java.util.List;

public record ChatCompletionRequest(
    List<PromptMessage> messages,
    String model,
    Double temperature,
    Integer maxTokens
) {}
