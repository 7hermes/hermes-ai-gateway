package io.tiger9.hermes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TextToSpeechRequest (
    String model,
    String input,
    String voice,
    @JsonProperty("response_format")
    String responseFormat,
    float speed
)
{}