package com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.mapper

import com.ideftbuild.ecommerce_backend.cart.domain.Cart
import com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.entity.CartEntity
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.VariantEntity
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity.UserEntity
import java.util.UUID


fun CartEntity.toDomain(): Cart = Cart(
    id = this.id,
    userId = this.user.id!!,
    items = this.items.map { it.toDomain() }.toMutableSet(),
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

fun Cart.toEntity(
    userRef: UserEntity,
    variantResolver: (UUID) -> VariantEntity
): CartEntity {

    val entity = CartEntity(
        id = id,
        user = userRef
    )

    entity.items.addAll(
        items.map {
            it.toEntity(
                cartRef = entity,
                variantRef = variantResolver(it.variant.id!!)
            )
        }
    )

    return entity
}
