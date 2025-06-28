package com.ds3c.apitcc.service

import com.ds3c.apitcc.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date

@Service
class JwtService(
    @Value("\${jwt.secret}") private val secretKey: String
) {
    private val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun generateToken(user: User) : String {
        return Jwts.builder()
            .subject(user.username)
            .claim("role", user.role.name)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 72))
            .signWith(key)
            .compact()
    }

    fun extractLogin(token: String) : String {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
            .subject
    }

    fun extractRole(token: String) : String {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
            .get("role", String::class.java)
    }

    fun isTokenValid(token: String, userDetails: UserDetails) : Boolean {
        val login = extractLogin(token)
        return login == userDetails.username
    }
}