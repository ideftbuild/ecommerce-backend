package com.ideftbuild.ecommerce_backend.product.api.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class UpdateProductRequest (
    @field:Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    val name: String?,

    @field:Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    val description: String?,

    @field:Size(max = 3, message = "Currency must be exactly 3 characters")
    val currency: String?
)
