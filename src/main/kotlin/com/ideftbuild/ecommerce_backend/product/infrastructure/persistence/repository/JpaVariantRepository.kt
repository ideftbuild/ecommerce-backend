package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.repository

import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.VariantEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.UUID

@Repository
interface JpaVariantRepository:
    JpaRepository<VariantEntity, UUID>,
    JpaSpecificationExecutor<VariantEntity> {
    fun findBySku(sku: String): VariantEntity?

    fun findByIdAndProduct_Id(id: UUID, productId: UUID): VariantEntity?

    fun findByIdAndProduct_IdAndDeletedAtIsNull(id: UUID, productId: UUID): VariantEntity?

    fun findByIdAndDeletedAtIsNull(id: UUID): VariantEntity?

//    fun findByProduct_IdAndDeletedAtIsNull(productId: UUID): List<VariantEntity>

    @EntityGraph(attributePaths = ["product"])
    override fun findAll(spec: Specification<VariantEntity>, pageable: Pageable): Page<VariantEntity>
}
