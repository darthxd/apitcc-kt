package com.ds3c.apitcc.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class TeacherDTO(
    @NotBlank
    val name: String?,
    @NotBlank
    val login: String?,
    @NotBlank
    @Min(8)
    val password: String?
)
