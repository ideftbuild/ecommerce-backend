package com.ideftbuild.ecommerce_backend.cart.domain

import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

class Cart(
    val id: UUID? = null,

    var userId: UUID,

    val items: MutableSet<CartItem> = mutableSetOf(),

    var createdAt: Instant? = null,

    var updatedAt: Instant? = null
) {
    companion object {
        fun create(userId: UUID): Cart = Cart(
            userId = userId
        )
    }

    fun addItem(cartItem: CartItem): Cart {
        println("receiving item in add Item method: ${cartItem.id}")
        this.items.add(cartItem)
        println("Added")
        return this
    }

    fun findItem(itemId: UUID): CartItem? = this.items.find { it.id == itemId }

    fun findItemByVariantId(variantId: UUID): CartItem? =
        items.firstOrNull { it.variant.id == variantId }

    fun getTotalItems(): Int = this.items.sumOf { it.quantity }

    fun getTotalPrice(): BigDecimal = this.items.sumOf {
        it.variant.price.amount.multiply(
            BigDecimal.valueOf(it.quantity.toLong())
        )
    }

    fun deleteItem(itemId: UUID): Boolean = this.items.removeIf { it.id == itemId }

    fun deleteItemByVariantId(variantId: UUID): Boolean = this.items.removeIf { it.variant.id == variantId }

    fun isOwner(userId: UUID): Boolean = this.userId == userId
}
