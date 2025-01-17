package io.tiger9.hermes.model;

import lombok.Data;

@Data
public class TextToSpeechRequest {
    private String model;
    private String input;
    private String voice;
    private String response_format;
    private Double speed;
} 