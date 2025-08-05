package com.project.CommunityManagementSystemAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.CommunityManagementSystemAPI.jwt.JWTEntryPoint;
import com.project.CommunityManagementSystemAPI.jwt.JWTFilter;
import com.project.CommunityManagementSystemAPI.jwt.JWTUtil;
import com.project.CommunityManagementSystemAPI.service.MyUserDetailsService;

import lombok.AllArgsConstructor;

// Security configuration class
@Configuration // Mark this class as a configuration class
@EnableWebSecurity // Enable Spring Security
@EnableMethodSecurity // Enable method-level security
@AllArgsConstructor // Lombok annotation to inject the constructor
public class SecurityConfig {

    // JWT entry point, util, and user details service are injected
    private final JWTEntryPoint jwtEntryPoint;
    private final JWTUtil jwtUtil;
    private final MyUserDetailsService userDetailsService;

    // JWT filter is created and returned
    @Bean
    public JWTFilter authenticationJwtTokenFilter() {
        return new JWTFilter(jwtUtil, userDetailsService);
    }

    // security filter chain is configured and returned
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // authorizing the endpoints
                .authorizeHttpRequests(requests -> requests
                        // public endpoints
                        .requestMatchers("/api/auth/**", "/api/profile/{username}", "/api/community/{searchByName}")
                        .permitAll()
                        .anyRequest().authenticated())

                // enabling basic authentication and disabling CSRF
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())

                // adding the JWT filter before the authentication filter
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)

                // configuring exception handling
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(jwtEntryPoint));

        return http.build();
    }

    // authentication manager is created and returned
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authenticationProvider);
    }

    // password encoder is created and returned
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}