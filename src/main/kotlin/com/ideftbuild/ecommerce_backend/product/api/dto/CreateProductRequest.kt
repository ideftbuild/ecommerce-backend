package com.ideftbuild.ecommerce_backend.product.api.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.util.UUID

class CreateProductRequest (
    @field:NotBlank(message = "Name is required")
    @field:Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    val name: String,

    @field:NotBlank(message = "Description is required")
    @field:Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    val description: String,

    @field:NotNull(message = "Variant is required")
    val variants: MutableList<CreateVariantRequest>,

    @field:NotNull(message = "Category is required")
    val categoryId: UUID,

    @field:NotBlank(message = "Currency is required")
    @field:Size(max = 3, message = "Currency must be exactly 3 characters")
    val currency: String,
) {
}
