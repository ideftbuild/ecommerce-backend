package com.ideftbuild.ecommerce_backend.cart.api.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.util.UUID

data class AddItemRequest(
    @field:NotNull(message = "Category is required")
    val variantId: UUID,

    @field:NotNull(message = "Quantity is required")
    @field:Min(value = 1, message = "Quantity must be at least 1")
    val quantity: Int
)
