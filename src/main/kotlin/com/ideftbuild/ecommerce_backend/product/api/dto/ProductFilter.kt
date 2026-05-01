package com.ideftbuild.ecommerce_backend.product.api.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.util.UUID

data class ProductFilter(
    val name: String? = null,

    @field:DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    val minPrice: BigDecimal? = null,

    val maxPrice: BigDecimal? = null,

    @field:NotBlank(message = "Currency is required")
    @field:Size(max = 3, message = "Currency must be exactly 3 characters")
    val currency: String? = null,

    val categoryId: UUID? = null,

    val categorySlug: String? = null
)
