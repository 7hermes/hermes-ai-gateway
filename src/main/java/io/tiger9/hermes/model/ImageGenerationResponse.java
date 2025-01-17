package io.tiger9.hermes.model;

import lombok.Data;
import java.util.List;

@Data
public class ImageGenerationResponse {
    private Long created;
    private List<Image> data;

    @Data
    public static class Image {
        private String url;
        private String b64_json;
        private String revised_prompt;
    }
} 