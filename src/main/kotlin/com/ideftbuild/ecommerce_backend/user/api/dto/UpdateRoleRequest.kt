package com.ideftbuild.ecommerce_backend.user.api.dto

import jakarta.validation.constraints.NotBlank

class UpdateRoleRequest(
    @field:NotBlank("Description is required")
    val description: String
)
