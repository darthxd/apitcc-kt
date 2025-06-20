package com.ds3c.apitcc.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Admin(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false, unique = true)
    var login: String,
    @Column(nullable = false)
    var password: String,
    val role: String = "ADMIN",
)