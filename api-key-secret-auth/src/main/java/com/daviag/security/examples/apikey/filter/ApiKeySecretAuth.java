package com.daviag.security.examples.apikey.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class ApiKeySecretAuth extends AbstractAuthenticationToken {

    private final ApiKeySecret apiKeySecret;

    public ApiKeySecretAuth(ApiKeySecret apiKeySecret) {
        this(apiKeySecret, Collections.emptyList());
    }
    public ApiKeySecretAuth(ApiKeySecret apiKeySecret, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiKeySecret = apiKeySecret;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return apiKeySecret.apiSecret();
    }

    @Override
    public Object getPrincipal() {
        return apiKeySecret.apiKey();
    }
}
