package com.ds3c.apitcc.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDate

@Entity
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false, unique = true)
    var rm: String,
    @Column(nullable = false, unique = true)
    var ra: String,
    @Column(nullable = false, unique = true)
    var cpf: String,
    @Column(nullable = false, unique = true)
    var email: String,
    @Column(nullable = false, unique = true)
    var phone: String,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id", nullable = false)
    var schoolclass: SchoolClass?,
    @Column(nullable = false)
    var birthdate: LocalDate,
    @Column(nullable = false)
    var password: String,
    var biometry: Long?,
    var photo: String?,
    var inschool: Boolean?
)