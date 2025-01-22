package io.tiger9.hermes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImageGenerationRequest(
    String model,
    String prompt,
    Integer n,
    Integer height,
    Integer width,
    String quality,
    String style,
    @JsonProperty("response_format")
    String responseFormat
) {} 