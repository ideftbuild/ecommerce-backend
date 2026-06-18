package com.ideftbuild.ecommerce_backend.user.api.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class SignUpRequest (

    @field:NotBlank("Username required")
    @field:Size(min = 3, max = 32, message = "Username must be between 3 to 32 characters")
    val username: String,

    @field:NotBlank("Firstname required")
    @field:Size(min = 3, max = 32, message = "FirstName must be between 3 to 32 characters")
    val firstName: String,

    @field:NotBlank("Lastname required")
    @field:Size(min = 3, max = 32, message = "Lastname must be between 3 to 32 characters")
    val lastName: String,

    @field:Email(message = "Invalid email format")
    @field:NotBlank(message = "Email required")
    val email: String,

    @field:NotBlank(message = "Password required")
//    @field:Pattern(
//        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$",
//        message = "Password must be at least 8 characters and include uppercase, lowercase, and a number"
//    )
    @field:Size(min = 8, message = "Password must be at least 8 characters")
    val password: String
)
