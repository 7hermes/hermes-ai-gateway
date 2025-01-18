package io.tiger9.hermes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AIServiceFactory {

    private final Map<String, AIService> aiServices;

    //TODO - Apply load balancing, fall back
    public AIService getService(String provider) {
        AIService service = aiServices.get(provider.toLowerCase());
        if (service == null) {
            throw new IllegalArgumentException("Unsupported provider: " + provider);
        }
        return service;
    }
}
