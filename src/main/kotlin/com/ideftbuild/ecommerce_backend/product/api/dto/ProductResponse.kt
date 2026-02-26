package com.ideftbuild.ecommerce_backend.product.api.dto

import com.ideftbuild.ecommerce_backend.product.domain.model.Product
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
    val price: BigDecimal,
    val currency: String,
    val quantity: Long,
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
                price = product.price.amount,
                currency = product.price.currency.currencyCode,
                quantity = product.quantity,
                updatedAt = product.updatedAt,
                createdAt = product.createdAt,
                deletedAt = product.deletedAt
            )
        }
    }
}
