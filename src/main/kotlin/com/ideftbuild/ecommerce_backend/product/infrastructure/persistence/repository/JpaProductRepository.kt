package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.repository

import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.UUID

@Repository
interface JpaProductRepository:
    JpaRepository<ProductEntity, UUID>,
    JpaSpecificationExecutor<ProductEntity> {
    fun findByName(name: String): List<ProductEntity>

    fun findByIdAndDeletedAtIsNull(id: UUID): ProductEntity?

    fun findByPriceGreaterThan(price: BigDecimal): List<ProductEntity>

    fun findAllByDeletedAtIsNull(spec: Specification<ProductEntity>, pageable: Pageable): Page<ProductEntity>
}
