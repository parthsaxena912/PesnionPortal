package com.drdo.pensionPortal.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ✅ Enable CORS
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            // ✅ Disable CSRF for simplicity (especially for REST APIs & H2)
            .csrf(csrf -> csrf.disable())

            // ✅ Allow H2 console to display properly
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))

            // ✅ Define what is publicly accessible
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**", "/ws/**", "/topic/**").permitAll()
                .requestMatchers("/api/**").permitAll()  // ✅ Allow all your REST endpoints (like pensioners)
                .anyRequest().authenticated()
            )

            // ✅ Disable login pages for API-based app
            .formLogin(login -> login.disable())
            .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }

    // ✅ Define CORS settings to allow Angular (localhost:4200)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

