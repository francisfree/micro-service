package com.example.card.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "createAuditorProvider")
public class AuditingConfig {

    @Bean
    public AuditorAware<String> createAuditorProvider() {
        return () -> {
            /*if (SecurityContextHolder.getContext().getAuthentication() != null) {
                if (SecurityContextHolder.getContext().getAuthentication() instanceof JwtAuthenticationToken auth) {
                    Object principal = auth.getPrincipal();
                    if (principal instanceof Jwt userDetails) {
                        return Optional.ofNullable((String) userDetails.getClaims().getOrDefault("userPublicId", ""));
                    } else {
                        return Optional.of("..");
                    }
                } else if (SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken auth) {
                    Object principal = auth.getPrincipal();
                    UserDetails userDetails = (UserDetails) principal;
                    return Optional.of(userDetails.getUsername());
                } else if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken auth) {
                    Object principal = auth.getPrincipal();
                    String userDetails = (String) principal;
                    return Optional.of(userDetails);
                } else {
                    return Optional.of("..");
                }
            } else {
                return Optional.of("..");
            }*/
            return Optional.of("anonymousUser");
        };
    }
}
