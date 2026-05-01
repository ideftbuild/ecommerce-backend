package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper

import com.ideftbuild.ecommerce_backend.product.domain.model.ProductQuery
import com.ideftbuild.ecommerce_backend.product.domain.model.VariantQuery
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.ProductEntity
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.VariantEntity
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

/**
 * Provides utility methods for building dynamic JPA [Specification] instances
 * for [VariantEntity].
 *
 * allowing flexible and composable product searches.
 */
object VariantSpecification {

    /**
     * Builds a [Specification] for [VariantEntity]
     *
     * This method dynamically constructs filtering criteria using
     * the non-null fields present in the query object.
     *
     * @return a [Specification] used to query [VariantEntity]
     */
    fun build(query: VariantQuery): Specification<VariantEntity> {

        return Specification { root, _, cb ->
            val predicates = mutableListOf<Predicate>()

            predicates.add(cb.isNull(root.get<Any>("deletedAt")))

            // Partial matching by sku
            query.sku?.let {
                predicates.add(cb.like(cb.lower(root.get("sku")), "%${it.lowercase()}%"))
            }

            query.quantity?.let {
                predicates.add(
                    cb.equal(root.get<Any>("quantity"), it)
                )
            }

            // Join all filters
            cb.and(*predicates.toTypedArray())
        }
    }
}
