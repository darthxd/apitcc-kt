package com.ds3c.apitcc.model

import com.ds3c.apitcc.enums.RolesEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
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
    override var username: String,
    @Column(nullable = false)
    override var password: String,
    @Enumerated(EnumType.STRING)
    override var role: RolesEnum = RolesEnum.ADMIN,
) : User