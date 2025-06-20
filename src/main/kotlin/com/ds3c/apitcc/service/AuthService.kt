package com.ds3c.apitcc.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.Date
import javax.crypto.SecretKey

@Service
class AuthService {
    private val secret = "everythingisgoingaccordingtoplanman"
    private val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
    private val expirationMillis = 86400000L

    fun generateToken(login: String) : String {
        val now = Date()
        val expiration = Date(now.time + expirationMillis)

        return Jwts.builder()
            .subject(login)
            .issuedAt(now)
            .expiration(expiration)
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    fun getLogin(token: String) : String? {
        return try {
            val jwt = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
            jwt.payload.subject
        } catch (e: Exception) {
            null
        }
    }
}