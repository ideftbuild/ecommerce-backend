package com.ideftbuild.ecommerce_backend.cart.domain

import com.ideftbuild.ecommerce_backend.product.domain.model.Variant
import com.ideftbuild.ecommerce_backend.user.domain.User
import java.time.Instant
import java.util.UUID

class CartItem(
    val id: UUID? = null,

    var cartId: UUID,

    var variant: Variant,

    var quantity: Int = 1,

    var updatedAt: Instant? = null,

    var createdAt: Instant? = null,
) {
    companion object {
        fun create(cartId: UUID, variant: Variant, quantity: Int): CartItem {
            require(quantity > 0) {
                "Quantity must be greater than zero."
            }

            return CartItem(
                cartId = cartId,
                variant = variant,
                quantity = quantity)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CartItem) return false

        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
