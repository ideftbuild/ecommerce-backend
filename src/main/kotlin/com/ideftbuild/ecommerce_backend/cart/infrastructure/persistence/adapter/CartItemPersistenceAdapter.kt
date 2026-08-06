package com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.adapter

import com.ideftbuild.ecommerce_backend.cart.application.port.output.CartItemOutputPort
import com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.repository.JpaCartRepository
import com.ideftbuild.ecommerce_backend.cart.domain.CartItem
import com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.mapper.toDomain
import com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.mapper.toEntity
import com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.repository.JpaCartItemRepository
import org.springframework.jdbc.core.JdbcTemplate

import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.repository.JpaVariantRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
    class CartItemPersistenceAdapter (
    private val cartItemJpaRepository: JpaCartItemRepository,
    private val cartJpaRepository: JpaCartRepository,
    private val jdbcTemplate: JdbcTemplate,
    private val variantJpaRepository: JpaVariantRepository,
): CartItemOutputPort {
    override fun save(cartItem: CartItem): CartItem {
        val cartRef = cartJpaRepository.getReferenceById(
            cartItem.cartId
        )

        val variantRef = variantJpaRepository.getReferenceById(
            cartItem.variant.id!!
        )

        val entity = cartItem.toEntity(
            cartRef = cartRef,
            variantRef = variantRef,
        )

        return cartItemJpaRepository.save(entity).toDomain()
    }

    override fun findById(id: UUID): CartItem? {
        val entity = cartItemJpaRepository.findById(id).orElse(null) ?: return null
        return entity.toDomain()
    }

    override fun deleteById(id: UUID) {
        cartItemJpaRepository.deleteById(id)
//        val rows = jdbcTemplate.update("DELETE FROM cart_items WHERE id = ?", id)
    }

    override fun existsById(id: UUID): Boolean {
        return cartItemJpaRepository.existsById(id)
    }
}
