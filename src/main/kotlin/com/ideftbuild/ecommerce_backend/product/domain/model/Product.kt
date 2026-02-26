package com.ideftbuild.ecommerce_backend.product.domain.model

import java.time.Instant
import java.util.UUID

class Product (
    val id: UUID?,

    val name: String,

    val description: String,

    val price: Money,

    val quantity: Long,

    var status: ProductStatus = ProductStatus.ACTIVE,

    var updatedAt: Instant? = null,

    var createdAt: Instant? = null,

    var deletedAt: Instant? = null,
) {

    companion object {
        fun create(
            id: UUID? = null,
            name: String,
            description: String,
            price: Money,
            quantity: Long,
        ): Product {
            return Product(
                id = id,
                name =  name,
                description = description,
                price = price,
                quantity = quantity,
            )
        }
    }

    fun softDelete(): Product {
        require(!isDeleted()) { "Product already deleted" }
        deletedAt = Instant.now()
        return this
    }

    fun restore(): Product {
        require(isDeleted()) { "Product is not deleted" }
        deletedAt = null
        return this
    }

    fun activate(): Product {
        require(!isActive()) { "Product already active" }
        status = ProductStatus.ACTIVE
        return this
    }

    fun deactivate(): Product {
        require(isActive()) { "Product is not active" }
        status = ProductStatus.INACTIVE
        return this
    }

    fun isDeleted(): Boolean = deletedAt != null

    fun isActive(): Boolean = status == ProductStatus.ACTIVE
}
