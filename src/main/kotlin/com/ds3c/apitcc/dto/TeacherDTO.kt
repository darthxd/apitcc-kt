package com.ds3c.apitcc.dto

import com.ds3c.apitcc.enums.RolesEnum
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class TeacherDTO(
    @NotBlank
    val name: String?,
    @NotBlank
    val username: String?,
    @NotBlank
    @Min(8)
    val password: String?,
)
