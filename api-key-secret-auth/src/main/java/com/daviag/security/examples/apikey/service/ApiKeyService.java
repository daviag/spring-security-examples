package com.daviag.security.examples.apikey.service;

import com.daviag.security.examples.apikey.filter.ApiKeySecret;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApiKeyService {

    private final Map<String, String> validApiKeys = new HashMap<>();

    public ApiKeyService() {
        validApiKeys.put("my-api-key", "my-api-secret");
    }

    public boolean isValidApiKey(ApiKeySecret apiKeySecret) {
        return validApiKeys.containsKey(apiKeySecret.apiKey())
                && validApiKeys.get(apiKeySecret.apiKey()).equals(apiKeySecret.apiSecret());
    }
}
