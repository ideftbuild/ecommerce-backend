package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper

import com.ideftbuild.ecommerce_backend.category.domain.Category
import com.ideftbuild.ecommerce_backend.category.infrastructure.persistence.entity.CategoryEntity
import com.ideftbuild.ecommerce_backend.product.domain.model.ProductQuery
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.ProductEntity
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification
import java.util.UUID

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
                predicates.add(cb.like(cb.lower(root.get("name")), "${it.lowercase()}%"))
            }

            // query.minPrice >= product.minPrice
            query.minPrice?.let {
                predicates.add(
                    cb.greaterThanOrEqualTo(root.get("minPrice"), it)
                )
            }

            query.maxPrice?.let {
                predicates.add(
                    cb.lessThanOrEqualTo(root.get("maxPrice"), it)
                )
            }

            query.currency?.let {
                predicates.add(
                    cb.equal(root.get<String>("currency"), it)
                )
            }

            query.categoryId?.let {
                predicates.add(
                    cb.equal(root.get<CategoryEntity>("category").get<UUID>("id"), it)
                )
            }
            query.categorySlug?.let {
                val categoryJoin = root.join<ProductEntity, CategoryEntity>("category")

                predicates.add(
                    cb.equal(categoryJoin.get<String>("slug"), it)
                )
            }

            // Join all filters
            cb.and(*predicates.toTypedArray())
        }
    }
}
