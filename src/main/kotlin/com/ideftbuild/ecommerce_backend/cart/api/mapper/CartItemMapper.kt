package com.ideftbuild.ecommerce_backend.cart.api.mapper

import com.ideftbuild.ecommerce_backend.cart.api.dto.CartItemResponse
import com.ideftbuild.ecommerce_backend.cart.domain.CartItem
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toResponse


fun CartItem.toResponse(): CartItemResponse = CartItemResponse(
    id = this.id,
    cartId = this.cartId,
    variant = this.variant.toResponse(),
    quantity = this.quantity,
    updatedAt = this.updatedAt,
    createdAt = this.createdAt
)
