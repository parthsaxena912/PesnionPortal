package com.drdo.pensionPortal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())      // ✅ Enable CORS
                .csrf(csrf -> csrf.disable())         // ✅ Disable CSRF

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().permitAll()
                )

                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .formLogin(login -> login.disable())
                .httpBasic(Customizer.withDefaults()); // ✅ Important

        return http.build();
    }
}
