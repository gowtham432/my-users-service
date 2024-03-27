package com.myapp.myusersservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/api/v1/auth/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers( "/", "/myUsers").permitAll()
                        .requestMatchers("/myUsers/getUserProfile/**").permitAll()
                        .requestMatchers("/myUsers/getAllDetails/**").permitAll()
                        .requestMatchers("/myUsers/createProfile").permitAll()
                        .requestMatchers("/myUsers/createAddress").permitAll()
                        .requestMatchers("/myUsers/updateAddress").permitAll()
                        .requestMatchers("/myUsers/updateOrderStatus/**").permitAll()
                        .requestMatchers("/myUsers/createOrder").permitAll()
                        .requestMatchers("/myUsers/deleteAddress/**").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
