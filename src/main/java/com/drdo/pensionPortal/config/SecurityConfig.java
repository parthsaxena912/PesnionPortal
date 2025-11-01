package com.drdo.pensionPortal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // ✅ Enable CORS (important!)
                .cors(cors -> cors.configurationSource(request -> null)) // Let CorsFilter handle everything

                // ✅ Disable CSRF for REST APIs
                .csrf(csrf -> csrf.disable())

                // ✅ Allow H2 console
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))

                // ✅ Public endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**", "/ws/**", "/topic/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().permitAll()
                )

                .formLogin(login -> login.disable())
                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }
}
