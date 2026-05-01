package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.adapter

import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import com.ideftbuild.ecommerce_backend.product.domain.model.ProductQuery
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.ProductSpecification
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toDomain
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toEntity
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.repository.JpaProductRepository
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Component
import java.util.Currency
import java.util.Locale
import java.util.UUID

@Component
class ProductPersistenceAdapter (
    private val jpaRepository: JpaProductRepository,
//    private val mapper: ProductMapper
): ProductOutputPort {
    override fun save(product: Product): Product {
        val entity = product.toEntity()
        println("product is: $entity")
        println("variants are:")
        entity.variants.forEach { println("sku: ${it.sku}") }
        val product = jpaRepository.save(entity).toDomain()

        println("after saving!!: ")
        product.variants.forEach { println("sku: ${it.sku}") }
        return product
    }

    override fun findById(id: UUID): Product? {
        val entity = jpaRepository.findByIdAndDeletedAtIsNull(id) ?: return null
        return entity.toDomain()
    }

    override fun findByIdIncludingDeleted(id: UUID): Product? {
        val entity = jpaRepository.findById(id).orElse(null) ?: return null
        return entity.toDomain()
    }

    override fun deleteById(id: UUID) {
        jpaRepository.deleteById(id)
    }

    override fun findAll(query: ProductQuery, pageable: Pageable): Page<Product> {
        val entities = jpaRepository.findAll(ProductSpecification.build(query), pageable)
        return entities.map {entity ->
            entity.toDomain()
        }
    }

    override fun existsById(id: UUID): Boolean {
        return jpaRepository.existsByIdAndDeletedAtIsNull(id)
    }

    override fun findCurrencyById(id: UUID): Currency? {
        val currencyStr =  jpaRepository.findCurrencyById(id) ?: return null
        return Currency.getInstance(currencyStr.uppercase(Locale.ROOT))
    }
}
