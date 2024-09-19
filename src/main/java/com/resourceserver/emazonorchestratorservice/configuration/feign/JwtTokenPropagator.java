package com.resourceserver.emazonorchestratorservice.configuration.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenPropagator implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken) {
            String jwtToken = ((JwtAuthenticationToken) authentication).getToken().getTokenValue();
            requestTemplate.header("Authorization", "Bearer " + jwtToken);
        }

    }
}
