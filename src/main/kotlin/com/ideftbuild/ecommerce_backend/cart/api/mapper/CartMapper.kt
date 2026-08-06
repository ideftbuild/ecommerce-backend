package com.ideftbuild.ecommerce_backend.cart.api.mapper

import com.ideftbuild.ecommerce_backend.cart.api.dto.CartResponse
import com.ideftbuild.ecommerce_backend.cart.domain.Cart
import java.math.BigDecimal


fun Cart.toResponse(totalItems: Int, totalPrice: BigDecimal): CartResponse = CartResponse(
    id = this.id,
    userId = this.userId,
    items = this.items.map { it.toResponse() },
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    totalItems = totalItems,
    totalPrice = totalPrice,
)
