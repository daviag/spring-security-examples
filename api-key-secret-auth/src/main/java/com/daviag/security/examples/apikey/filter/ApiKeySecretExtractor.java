package com.daviag.security.examples.apikey.filter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApiKeySecretExtractor {
    public static final String API_KEY_HEADER = "X-API-Key";
    public static final String API_SECRET_HEADER = "X-API-Secret";

    public Optional<ApiKeySecret> extractApiKeySecret(HttpServletRequest request) {
        String apiKey = request.getHeader(API_KEY_HEADER);
        String apiSecret = request.getHeader(API_SECRET_HEADER);

        if (apiKey != null && apiSecret != null) {
            return Optional.of(new ApiKeySecret(apiKey, apiSecret));
        }

        return Optional.empty();
    }
}
