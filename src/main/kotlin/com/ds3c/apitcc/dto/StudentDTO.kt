package com.ds3c.apitcc.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class StudentDTO(
    @NotBlank
    val name: String? = null,
    @NotBlank
    @Max(5)
    val rm: String? = null,
    @NotBlank
    @Max(12)
    val ra: String? = null,
    @NotBlank
    @Max(11)
    val cpf: String? = null,
    @NotBlank
    val email: String? = null,
    @NotBlank
    @Max(11)
    val phone: String? = null,
    @NotBlank
    val schoolclassId: Long? = null,
    @NotBlank
    val birthdate: LocalDate? = null,
    @NotBlank
    val password: String? = null,
    val biometry: Long? = null,
    val photo: String? = null,
    val inschool: Boolean? = null
)