package com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.mapper

import com.ideftbuild.ecommerce_backend.cart.domain.CartItem
import com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.entity.CartEntity
import com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.entity.CartItemEntity
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.VariantEntity
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toDomain
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toEntity


fun CartItemEntity.toDomain(): CartItem = CartItem(
    id = this.id,
    cartId = this.cart.id!!,
    variant = this.variant.toDomain(),
    quantity = this.quantity,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

fun CartItem.toEntity(
    cartRef: CartEntity,
    variantRef: VariantEntity): CartItemEntity = CartItemEntity(
    id = this.id,
    cart = cartRef,
    variant = variantRef,
    quantity = this.quantity
)
