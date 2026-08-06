package com.ideftbuild.ecommerce_backend.cart.api.dto

import com.ideftbuild.ecommerce_backend.cart.domain.CartItem
import com.ideftbuild.ecommerce_backend.user.domain.User
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

data class CartResponse (
    val id: UUID? = null,

    var userId: UUID,

    val items: List<CartItemResponse> = mutableListOf(),

    val totalItems: Int,

    val totalPrice: BigDecimal,

    var createdAt: Instant? = null,

    var updatedAt: Instant? = null
)
