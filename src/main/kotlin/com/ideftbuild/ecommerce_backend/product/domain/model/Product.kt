package com.ideftbuild.ecommerce_backend.product.domain.model

import com.ideftbuild.ecommerce_backend.category.domain.Category
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import java.math.BigDecimal
import java.time.Instant
import java.util.Currency
import java.util.TreeSet
import java.util.UUID

class Product (
    val id: UUID? = null,

    var name: String,

    var description: String,

    var currency: Currency,

    var status: ProductStatus = ProductStatus.ACTIVE,

    var minPrice: Money,

    var maxPrice: Money,

    var category: Category,

    var updatedAt: Instant? = null,

    var createdAt: Instant? = null,

    var deletedAt: Instant? = null,

    ) {

    private val priceComparator = Comparator<Variant> { v1, v2 ->
        v1.price.amount.compareTo(v2.price.amount)
    }

    private val _variants: TreeSet<Variant> = TreeSet(priceComparator)

    private var _variantsCache: List<Variant>? = null

    // Active variants cache
    val activeVariants: List<Variant>
        get() {
            if (_variantsCache == null) {
                _variantsCache = _variants.filter { !it.isDeleted() }
            }
            return _variantsCache!!
        }

    val variants: Set<Variant>
        get() {
            return _variants
        }

    val variantsCache: List<Variant>?
        get() {
            return _variantsCache
        }

    companion object {
        fun create(
            name: String,
            description: String,
            currency: Currency,
            variants: MutableList<Variant>,
            category: Category
//            price: Money,
//            quantity: Long,
        ): Product {
            require(variants.isNotEmpty()) { "Product must have at least one variant" }


            val product = Product(
                name = name,
                description = description,
                currency = currency,
                category = category,
                minPrice = variants.minOf { it.price },
                maxPrice = variants.maxOf { it.price }
            )

            variants.forEach { product.addVariant(it) }
            return product
        }
    }

    fun update(
        name: String? = null,
        description: String? = null,
        currency: String? = null
    ): Product {
        val currency = Money.resolveCurrency(currency)
        this.apply {
            name?.let { this.name = it }
            description?.let { this.description = it }
            currency?.let { this.currency = it }
        }
        return this
    }

    fun addVariant(variant: Variant): Product {
        require(activeVariants.size < 11) { "Product can only have up to 10 variants" }

        println("Adding new variant ${variant.sku} to product : ${this.id}")
        variant.productId = this.id
        _variants.add(variant)

        invalidateCache()
        println("invalidated cache")
        recalculatePrices()
        println("recalculated price")
        return this
    }

    fun findVariantBySku(variant: Variant): Variant? {
        return activeVariants.find { it.id ==  variant.id}
    }

    fun removeVariant(variantId: UUID): Product {
        require(activeVariants.size > 1) { "Product must have at least 1 variant" }

        val variant = findVariantById(variantId)
            ?: throw ResourceNotFoundException("variant", variantId)

        variant.softDelete()

        invalidateCache()
        recalculatePrices()
        return this
    }

    private fun recalculatePrices() {
        val active = activeVariants

        if (active.isEmpty()) {
            minPrice = Money.of(BigDecimal.ZERO, currency)
            maxPrice = Money.of(BigDecimal.ZERO, currency)
            return
        }

        minPrice = active.first().price
        maxPrice = active.last().price
    }

    fun softDelete(): Product {
        require(!isDeleted()) { "Product already deleted" }

        deletedAt = Instant.now()  // soft delete product
        // soft delete all variants
        _variants.forEach {
            // ignore errors from already deleted variants
            runCatching { it.softDeleteByParent() }
        }
        invalidateCache()
        return this
    }

    fun restore(): Product {
        require(isDeleted()) { "Product is not deleted" }
        deletedAt = null
        // restore only variants that was deleted by product deletion
        _variants.forEach {
            if (it.deletedByParent) {
                it.restore()
            }
        }
        invalidateCache()
        return this
    }

    fun findVariantBySku(sku: String): Variant? {
        return activeVariants.find { it.sku == sku }
    }

    fun findVariantById(id: UUID): Variant? {
        return activeVariants.find { it.id == id }
    }

    fun activate(): Product {
        require(!isActive()) { "Product already active" }
        status = ProductStatus.ACTIVE
        return this
    }

    fun invalidateCache() = run { _variantsCache = null }


    fun deactivate(): Product {
        require(isActive()) { "Product is not active" }
        status = ProductStatus.INACTIVE
        return this
    }

    fun isDeleted(): Boolean = deletedAt != null

    fun isActive(): Boolean = status == ProductStatus.ACTIVE
}
