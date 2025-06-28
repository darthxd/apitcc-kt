package com.ds3c.apitcc.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    val jwtAuthFilter: JwtAuthFilter
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it
                    .anyRequest().permitAll()
                    // .requestMatchers("/api/auth/**").permitAll()
                    // .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
                    // .requestMatchers("/api/student/**").hasAuthority("STUDENT")
                    // .requestMatchers("/api/teacher/**").hasAuthority("TEACHER")
                    // .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}