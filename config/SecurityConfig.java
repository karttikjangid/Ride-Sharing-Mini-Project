package com.choudharykhushboo.RideShare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.choudharykhushboo.RideShare.config.JWTAuthFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {
    @Autowired
    private JWTAuthFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                                auth
                                        .requestMatchers("/api/auth/**").permitAll()
                                        .requestMatchers("/api/v1/driver/**").hasRole("DRIVER")
                                        .requestMatchers("/api/v1/user/**").hasRole("USER")
                                        .requestMatchers("/api/v1/rides/**").hasAnyRole("DRIVER","USER")
                                        .anyRequest().authenticated()
                        );
        httpSecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
