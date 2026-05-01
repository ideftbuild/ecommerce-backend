package com.ideftbuild.ecommerce_backend.product.api.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import java.math.BigDecimal

class UpdateVariantRequest (
    @field:DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    val price: BigDecimal? = null,

    @field:Min(value = 1, message = "Quantity must be at least 1")
    val quantity: Long? = null
)
