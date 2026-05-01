package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.adapter

import com.ideftbuild.ecommerce_backend.product.application.port.output.VariantOutputPort
import com.ideftbuild.ecommerce_backend.product.domain.model.Variant
import com.ideftbuild.ecommerce_backend.product.domain.model.VariantQuery
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.VariantSpecification
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toDomain
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toEntity
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.repository.JpaProductRepository
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.repository.JpaVariantRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.util.UUID


@Component
class VariantPersistenceAdapter(
    private val variantJpaRepository: JpaVariantRepository,
    private val productJpaRepository: JpaProductRepository
): VariantOutputPort {

    override fun save(variant: Variant): Variant {
        val productRef = productJpaRepository.getReferenceById(
            variant.productId!!
        )

         return variantJpaRepository.save(variant.toEntity(
             productEntity = productRef
         )).toDomain(variant.price.currency.currencyCode)
    }

    override fun findById(id: UUID): Variant? {
        val entity = variantJpaRepository.findByIdAndDeletedAtIsNull(id) ?: return null
        return entity.toDomain(entity.product.currency)
    }

    override fun findBySku(sku: String): Variant? {
        val entity = variantJpaRepository.findBySku(sku) ?: return null
        return entity.toDomain(entity.product.currency)
    }

    override fun findByIdAndProductId(
        variantId: UUID,
        productId: UUID
    ): Variant? {
        val entity = variantJpaRepository.findByIdAndProduct_IdAndDeletedAtIsNull(
            variantId, productId)
            ?: return null
        return entity.toDomain(entity.product.currency)
    }

    override fun findByIdAndProductIdIncludingDeleted(
        variantId: UUID,
        productId: UUID
    ): Variant? {
        val entity = variantJpaRepository.findByIdAndProduct_Id(
            variantId, productId)
            ?: return null
        return entity.toDomain(entity.product.currency)
    }

    override fun findByProductId(productId: UUID): List<Variant>? {
        val product = productJpaRepository.findByIdAndDeletedAtIsNull(productId) ?: return null
        return product.toDomain().activeVariants
    }

    override fun findAll(query: VariantQuery, pageable: Pageable): Page<Variant> {
        println("Passing query with sku: ${query.sku} and ${query.quantity}")
        val entities = variantJpaRepository.findAll(VariantSpecification.build(query), pageable)
        return entities.map { entity ->
            entity.toDomain(
                currency = entity.product.currency
            )
        }
    }
}
