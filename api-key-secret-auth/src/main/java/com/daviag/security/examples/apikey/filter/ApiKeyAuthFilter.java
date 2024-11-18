package com.daviag.security.examples.apikey.filter;

import com.daviag.security.examples.apikey.service.ApiKeyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private final ApiKeySecretExtractor apiKeySecretExtractor;
    private final ApiKeyService apiKeyService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Optional<ApiKeySecret> apiKeySecret = apiKeySecretExtractor.extractApiKeySecret(request);

        if (apiKeySecret.isPresent() && apiKeyService.isValidApiKey(apiKeySecret.get())) {
            ApiKeySecretAuth auth = new ApiKeySecretAuth(apiKeySecret.get());
            SecurityContextHolder.getContext().setAuthentication(auth);
            log.info("Api key authenticated successfully");
        }
        filterChain.doFilter(request, response);
    }
}
