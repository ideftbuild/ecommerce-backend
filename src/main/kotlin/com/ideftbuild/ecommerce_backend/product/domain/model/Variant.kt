package com.ideftbuild.ecommerce_backend.product.domain.model

import java.math.BigDecimal
import java.time.Instant
import java.util.Currency
import java.util.UUID

class Variant (
    var id: UUID? = null,

    val sku: String,

    var price: Money,

    val attributes: Map<String, Any> = emptyMap(),

    var quantity: Long,

    var productId: UUID? = null,

    var images: MutableList<VariantImage> = mutableListOf(),

    var deletedAt: Instant? = null,

    var deletedByParent: Boolean = false
) {

    fun softDelete(): Variant {
        require(!isDeleted()) { "Variant already deleted"}
        deletedAt = Instant.now()
        deletedByParent = false
        return this
    }

    fun softDeleteByParent(): Variant {
        require(!isDeleted() && !wasDeletedIndependently()) { "Variant already deleted"}
        deletedAt = Instant.now()
        deletedByParent = true
        return this
    }

    fun uploadImage(key: String, url: String, originalFilename: String?): Variant {
        require(this.images.size < 5)

        this.images.add(VariantImage(
            url = url,
            originalName = originalFilename ?: "file",
            storageKey = key,
            sortOrder = this.images.size + 1,
            variantId = this.id
        ))
        return this
    }

    fun restore(): Variant {
        require(isDeleted()) { "Variant is not deleted"}
        deletedAt = null
        deletedByParent = false
        return this
    }

    fun isDeleted(): Boolean = deletedAt != null

    fun wasDeletedIndependently(): Boolean = this.isDeleted() && !deletedByParent

    fun update(price: BigDecimal? = null, quantity: Long? = null) {
        // validations
        if (price != null) require(price >= BigDecimal.ZERO ) { "Price must be positive" }
        if (quantity != null) require(quantity > 0) { "Quantity should be greater than zero" }

        // update
        price?.let {
            this.price = Money.of(it, this.price.currency)
        }
        quantity?.let {
            this.quantity = it
        }
    }

    companion object {
        fun create(
            sku: String,
            quantity: Long,
            price: BigDecimal,
            currency: Currency,
            attributes: Map<String, Any>,
            productId: UUID): Variant {

            return Variant(
                sku = sku,
                quantity = quantity,
                price = Money.of(price, currency),
                attributes = attributes,
                productId = productId
            )
        }
    }
}
