package com.ds3c.apitcc.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
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
                    .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()

                    // Admin routes
                    .requestMatchers("/api/admin/**").hasAuthority("ADMIN")

                    // Student routes
                    .requestMatchers(HttpMethod.GET, "/api/student/").hasAnyAuthority("ADMIN","TEACHER")
                    .requestMatchers(HttpMethod.GET, "/api/student/{id}").hasAnyAuthority("ADMIN","TEACHER","STUDENT")
                    .requestMatchers(HttpMethod.GET, "/api/student/{filter}/{value}").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/student/").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/student/").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/student/").hasAuthority("ADMIN")

                    // Teacher routes
                    .requestMatchers("/api/teacher/**").hasAuthority("ADMIN")

                    // School class routes
                    .requestMatchers("/api/class/**").hasAuthority("ADMIN")

                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}