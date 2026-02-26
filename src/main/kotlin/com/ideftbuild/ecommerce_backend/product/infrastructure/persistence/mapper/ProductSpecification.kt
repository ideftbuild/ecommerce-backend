package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper

import com.ideftbuild.ecommerce_backend.product.domain.model.ProductQuery
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.ProductEntity
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

/**
 * Provides utility methods for building dynamic JPA [Specification] instances
 * for [ProductEntity].
 *
 * This object translates a [ProductQuery] into database filtering criteria,
 * allowing flexible and composable product searches.
 */
object ProductSpecification {

    /**
     * Builds a [Specification] for [ProductEntity] based on the provided [ProductQuery].
     *
     * This method dynamically constructs filtering criteria using
     * the non-null fields present in the query object.
     *
     * @param query the [ProductQuery] containing filter criteria
     * @return a [Specification] used to query [ProductEntity]
     */
    fun build(query: ProductQuery): Specification<ProductEntity> {

        return Specification { root, _, cb ->
            val predicates = mutableListOf<Predicate>()

            predicates.add(cb.isNull(root.get<Any>("deletedAt")))

            // Partial matching by name
            query.name?.let {
                predicates.add(cb.like(cb.lower(root.get("name")), "%${it.lowercase()}%"))
            }

            // price >= minPrice
            query.minPrice?.let {
                predicates.add(
                    cb.greaterThanOrEqualTo(root.get("price"), it)
                )
            }

            // price <= maxPrice
            query.maxPrice?.let {
                predicates.add(
                    cb.lessThanOrEqualTo(root.get("price"), it)
                )
            }

            // Join all filters
            cb.and(*predicates.toTypedArray())
        }
    }
}
