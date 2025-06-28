package com.ds3c.apitcc.controller

import com.ds3c.apitcc.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class LoginController(
    val authService: AuthService
) {
    data class LoginRequest(val username: String, val password: String)
    data class LoginResponse(val token: String)

    @PostMapping("/admin")
    fun adminLogin(@RequestBody request: LoginRequest) : ResponseEntity<LoginResponse> {
        val token = authService.adminLogin(request.username, request.password)
        return ResponseEntity.ok(LoginResponse(token))
    }

    @PostMapping("/student")
    fun studentLogin(@RequestBody request: LoginRequest) : ResponseEntity<LoginResponse> {
        val token = authService.studentLogin(request.username, request.password)
        return ResponseEntity.ok(LoginResponse(token))
    }

    @PostMapping("/teacher")
    fun teacherLogin(@RequestBody request: LoginRequest) : ResponseEntity<LoginResponse> {
        val token = authService.teacherLogin(request.username, request.password)
        return ResponseEntity.ok(LoginResponse(token))
    }
}