package com.ideftbuild.ecommerce_backend.product.api.dto

import com.ideftbuild.ecommerce_backend.product.domain.model.Variant
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.util.UUID

class CreateVariantRequest (
    @field:Size(min = 7, max = 50, message = "Name must be between 7 and 50 characters")
    @field:Pattern(
        regexp = "^[A-Z0-9_-]{4,20}$",
        message = "Sku must be 4-20 characters and contain only uppercase letters, numbers, underscores and hyphens"
    )
    val sku: String? = null,

    @field:NotNull(message = "Price is required")
    @field:DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    val price: BigDecimal,

    @field:NotNull(message = "Quantity is required")
    @field:Min(value = 1, message = "Quantity must be at least 1")
    val quantity: Long,

    val attributes: Map<String, Any>? = emptyMap(),
)

fun test() = CreateVariantRequest(
    sku = UUID.randomUUID().toString(),
    price = BigDecimal("200.00"),
    quantity = 20
//    attributes = TODO()
)

