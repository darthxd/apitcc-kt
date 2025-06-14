package com.ds3c.apitcc.model

import com.ds3c.apitcc.enums.CoursesEnum
import com.ds3c.apitcc.enums.GradesEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class SchoolClass(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false, unique = true)
    var name: String,
    @Column(nullable = false)
    var grade: GradesEnum,
    @Column(nullable = false)
    var course: CoursesEnum
)
