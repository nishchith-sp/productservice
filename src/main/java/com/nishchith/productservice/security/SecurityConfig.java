package com.nishchith.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/products").permitAll()
//                        .authenticated()
//                        .hasAuthority("SCOPE_ADMIN")
//                        .anyRequest().permitAll()
//                )
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));


        return http.authorizeHttpRequests(request -> {
                    try {
                        request.anyRequest().permitAll()
                                .and().cors().disable()
                                .csrf().disable();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .build();
    }

}
