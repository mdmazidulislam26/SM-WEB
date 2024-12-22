package com.mazid.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

// Configuration class for security settings
@Configuration
@EnableWebSecurity// Enables Spring Security for the application
public class AppConfig {
    // Configures HTTP security for the application
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       http.sessionManagement(management -> management.sessionCreationPolicy(
               SessionCreationPolicy.STATELESS))// Sets session management to stateless for REST APIs
               .authorizeHttpRequests(Authorize -> Authorize
                .requestMatchers("/api/**").authenticated()// Protects API endpoints
                .anyRequest().permitAll())// Allows access to all other endpoints
               .addFilterBefore(new jwtValidator(), BasicAuthenticationFilter.class)// Adds custom JWT validation filter
                .csrf(csrf -> csrf.disable())// Disables CSRF protection for stateless APIs
               .cors(cors -> cors.configurationSource(corsConfigurationSource()));// Configures CORS settings

        return http.build();
    }

    // Configures CORS (Cross-Origin Resource Sharing) settings
    private CorsConfigurationSource corsConfigurationSource() {

        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));// Specifies allowed origins
                cfg.setAllowedMethods(Collections.singletonList("*"));// Allows all HTTP methods
                cfg.setAllowCredentials(true);// Allows credentials such as cookies
                cfg.setAllowedHeaders(Collections.singletonList("*"));// Allows all headers
                cfg.setExposedHeaders(Arrays.asList(("Authorization")));// Exposes Authorization header
                cfg.setMaxAge(3600L);// Sets max age for caching preflight requests

                return cfg;
            }
        };
    }

    // Configures a password encoder bean using BCrypt
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
