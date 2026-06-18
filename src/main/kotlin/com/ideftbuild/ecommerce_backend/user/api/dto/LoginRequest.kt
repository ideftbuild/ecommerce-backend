package com.ideftbuild.ecommerce_backend.user.api.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginRequest (

    @field:Email(message = "Invalid email format")
    val email: String?,

    val username: String?,

    val password: String
)
