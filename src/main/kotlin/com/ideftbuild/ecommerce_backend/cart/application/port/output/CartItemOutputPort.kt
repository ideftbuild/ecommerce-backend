package com.ideftbuild.ecommerce_backend.cart.application.port.output

import com.ideftbuild.ecommerce_backend.cart.domain.CartItem
import java.util.UUID

interface CartItemOutputPort {
    fun save(cartItem: CartItem): CartItem

    fun findById(id: UUID): CartItem?

    fun deleteById(id: UUID)

    fun existsById(id: UUID): Boolean
}
