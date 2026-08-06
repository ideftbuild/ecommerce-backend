package com.ideftbuild.ecommerce_backend.cart.application.port.input

import com.ideftbuild.ecommerce_backend.cart.api.dto.CartItemResponse
import java.util.UUID

interface UpdateCartItemQuantityInputPort {
    fun execute(userId: UUID, itemId: UUID, quantity: Int): CartItemResponse
}
