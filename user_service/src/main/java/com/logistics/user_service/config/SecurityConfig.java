package com.logistics.user_service.config;

import com.logistics.user_service.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {


        //disabling csrf token for making post requests
        return http.csrf(csrf->csrf.disable())

                // this is some session stuff idk about it yet
                .sessionManagement(
            session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS))


                //here we are permiting and authentication requests

                .authorizeHttpRequests(
                        auth -> auth.
                                requestMatchers("/v1/api/auth/**").
                                permitAll()

                                .requestMatchers(
                                        "/v1/api/admin/**")
                                .hasRole("ADMIN")

                                .requestMatchers(
                                        "/v1/api/driver/**")
                                .hasRole("DRIVER")


                                .anyRequest().
                                authenticated())

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
