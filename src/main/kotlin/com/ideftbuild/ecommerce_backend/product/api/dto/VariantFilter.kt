package com.ideftbuild.ecommerce_backend.product.api.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

class VariantFilter (
//    @field:Size(min = 7, max = 50, message = "Name must be between 7 and 50 characters")
//    @field:Pattern(
//        regexp = "^[A-Z0-9_-]{4,20}$",
//        message = "Sku must be 4-20 characters and contain only uppercase letters, numbers, underscores and hyphens"
//    )
    val sku: String? = null,

    @field:Min(value = 1, message = "Quantity must be at least 1")
    val quantity: Long? = null,
)

