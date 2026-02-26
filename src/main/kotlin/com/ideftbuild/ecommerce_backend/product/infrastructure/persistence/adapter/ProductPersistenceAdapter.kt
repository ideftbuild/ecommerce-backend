package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.adapter

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductFilter
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import com.ideftbuild.ecommerce_backend.product.domain.model.ProductQuery
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.ProductEntity
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.ProductMapper
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.ProductSpecification
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.repository.JpaProductRepository
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ProductPersistenceAdapter (
    private val jpaRepository: JpaProductRepository,
    private val mapper: ProductMapper
): ProductOutputPort {
    override fun save(product: Product): Product {
        var product = mapper.toEntity(product)
        print("product is: $product")
        println("deleted at is: ${product.deletedAt}")
        println("created at is: ${product.createdAt}")
        product = jpaRepository.save(product)
        return mapper.toDomain(product)
    }

    override fun findById(id: UUID): Product? {
        val entity = jpaRepository.findByIdAndDeletedAtIsNull(id) ?: return null
        return mapper.toDomain(entity)
    }

    override fun findByIdIncludingDeleted(id: UUID): Product? {
        val entity = jpaRepository.findById(id).orElse(null) ?: return null
        return mapper.toDomain(entity)
    }

    override fun deleteById(id: UUID) {
        try {
            jpaRepository.deleteById(id)
        } catch (ex: EmptyResultDataAccessException) {
            throw ResourceNotFoundException("product", id)
        }
    }

    override fun findAll(query: ProductQuery, pageable: Pageable): Page<Product> {
        val entities = jpaRepository.findAll(ProductSpecification.build(query), pageable)
        return entities.map {entity ->
            mapper.toDomain(entity)
        }
    }

}
