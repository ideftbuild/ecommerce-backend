package com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.adapter

import com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.repository.JpaCartRepository
import com.ideftbuild.ecommerce_backend.cart.application.port.output.CartOutputPort
import com.ideftbuild.ecommerce_backend.cart.domain.Cart
import com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.mapper.toDomain
import com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.mapper.toEntity

import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toDomain
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toEntity
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.repository.JpaVariantRepository
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.repository.UserRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CartPersistenceAdapter (
    private val variantJpaRepository: JpaVariantRepository,
    private val userJpaRepository: UserRepository,
    private val cartJpaRepository: JpaCartRepository,
): CartOutputPort {
    override fun save(cart: Cart): Cart {

        val userRef = userJpaRepository.getReferenceById(
            cart.userId
        )

        val entity = cart.toEntity(userRef) {
            variantJpaRepository.getReferenceById(it)
        }

        return cartJpaRepository.save(entity).toDomain()
    }

    override fun findById(id: UUID): Cart? {
        val entity = cartJpaRepository.findById(id).orElse(null) ?: return null
        return entity.toDomain()
    }

    override fun deleteById(id: UUID) {
        cartJpaRepository.deleteById(id)
    }

    override fun existsById(id: UUID): Boolean {
        return cartJpaRepository.existsById(id)
    }

    override fun findByUserId(id: UUID): Cart? {
        return cartJpaRepository.findByUser_Id(id)?.toDomain()
    }
}
