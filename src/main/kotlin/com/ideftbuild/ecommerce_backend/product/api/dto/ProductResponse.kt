package com.ideftbuild.ecommerce_backend.product.api.dto

import com.ideftbuild.ecommerce_backend.category.api.dto.CategoryResponse
import com.ideftbuild.ecommerce_backend.category.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import com.ideftbuild.ecommerce_backend.product.domain.model.Variant
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toResponse
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

@Relation(collectionRelation = "products", itemRelation = "product")
class ProductResponse(
    val id: UUID?,
    val name: String,
    val description: String,
//    val price: BigDecimal,
    val currency: String,
//    val quantity: Long,
    val status: String,

    val variants: List<VariantResponse>,

    val category: CategoryResponse,

    val minPrice: BigDecimal,

    val maxPrice: BigDecimal,

    val updatedAt: Instant?,

    val createdAt: Instant?,

    val deletedAt: Instant?,
): RepresentationModel<ProductResponse>() {
    companion object {
        fun from(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id,
                name = product.name,
                description = product.description,
                currency = product.currency.currencyCode,
                status = product.status.name,
                variants = product.activeVariants.map { it.toResponse() },
                category = product.category.toResponse(),
                updatedAt = product.updatedAt,
                createdAt = product.createdAt,
                deletedAt = product.deletedAt,
                minPrice = product.minPrice.amount,
                maxPrice = product.maxPrice.amount
            )
        }
    }
}
