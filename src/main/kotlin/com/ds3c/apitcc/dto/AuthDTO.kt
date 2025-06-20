package com.ds3c.apitcc.dto

data class AuthRequest(
    val login: String,
    val password: String
)
data class AuthResponse(
    val token: String
)
