package com.ideftbuild.ecommerce_backend.cart.api.dto

import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import java.time.Instant
import java.util.UUID

data class CartItemResponse(
    val id: UUID? = null,

    var cartId: UUID,

    var variant: VariantResponse,

    var quantity: Int,

    var updatedAt: Instant? = null,

    var createdAt: Instant? = null,
)
