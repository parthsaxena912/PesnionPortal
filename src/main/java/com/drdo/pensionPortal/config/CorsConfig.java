package com.drdo.pensionPortal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // ✅ Your frontend origins
        config.setAllowedOrigins(List.of(
                "http://localhost:4200",
                "https://dodo-frontendvercel.vercel.app"
        ));

        // ✅ Allow headers
        config.setAllowedHeaders(List.of("*"));

        // ✅ Allow all methods
        config.setAllowedMethods(List.of("*"));

        // ✅ Allow credentials
        config.setAllowCredentials(true);

        // ✅ IMPORTANT — expose headers (needed for JWT)
        config.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}

