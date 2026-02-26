package com.ideftbuild.ecommerce_backend.product.api.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

class CreateProductRequest {
    @field:NotBlank(message = "Name is required")
    @field:Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    val name: String

    @field:NotBlank(message = "Description is required")
    @field:Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    val description: String

    @field:NotNull(message = "Price is required")
    @field:DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    val price: BigDecimal

    @field:NotBlank(message = "Currency is required")
    @field:Size(max = 3, message = "Currency must be exactly 3 characters")
    val currency: String

    @field:NotNull(message = "Quantity is required")
    @field:Min(value = 1, message = "Quantity must be at least 1")
    val quantity: Long

    constructor(
        name: String,
        description: String,
        price: BigDecimal,
        currency: String,
        quantity: Long
    ) {
        this.name = name
        this.description = description
        this.price = price
        this.quantity = quantity
        this.currency = currency
    }
}
