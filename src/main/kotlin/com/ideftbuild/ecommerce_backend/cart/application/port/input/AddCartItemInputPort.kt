package com.ideftbuild.ecommerce_backend.cart.application.port.input

import com.ideftbuild.ecommerce_backend.cart.api.dto.CartItemResponse
import com.ideftbuild.ecommerce_backend.cart.api.dto.CartResponse
import java.util.UUID

interface AddCartItemInputPort {
    fun execute(userId: UUID, variantId: UUID, quantity: Int): CartResponse
}
