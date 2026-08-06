package com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.repository

import com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.entity.CartEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface JpaCartRepository: JpaRepository<CartEntity, UUID> {
    fun findByUser_Id(userId: UUID): CartEntity?
}
