package com.ds3c.apitcc.config

import com.ds3c.apitcc.service.JwtService
import com.ds3c.apitcc.service.CustomUserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    val jwtService: JwtService,
    val userDetailsService: CustomUserDetailsService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        val token = authHeader?.takeIf { it.startsWith("Bearer ") }?.substring(7)

        if(token != null) {
            val username = jwtService.extractLogin(token)
            val userDetails = userDetailsService.loadUserByUsername(username)

            if(jwtService.isTokenValid(token, userDetails)) {
                val auth = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                auth.details = WebAuthenticationDetailsSource()
                    .buildDetails(request)
                SecurityContextHolder.getContext().authentication = auth
            }
        }

        filterChain.doFilter(request, response)
    }
}