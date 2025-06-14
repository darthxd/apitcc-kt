package com.ds3c.apitcc.dto

import com.ds3c.apitcc.enums.CoursesEnum
import com.ds3c.apitcc.enums.GradesEnum
import jakarta.validation.constraints.NotBlank

data class SchoolClassDTO(
    @NotBlank
    val name: String? = null,
    @NotBlank
    val grade: GradesEnum? = null,
    @NotBlank
    val course: CoursesEnum? = null
)