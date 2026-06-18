package com.ideftbuild.ecommerce_backend.user.api.dto

import jakarta.validation.constraints.NotBlank

class CreateRoleRequest(
    @field:NotBlank(message = "Name required")
    val name: String,

    val description: String?
)
