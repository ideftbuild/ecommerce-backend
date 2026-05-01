package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper

import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.domain.model.Money
import com.ideftbuild.ecommerce_backend.product.domain.model.Variant
import com.ideftbuild.ecommerce_backend.product.domain.model.VariantImage
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.ProductEntity
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.VariantEntity
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.VariantImageEntity
import java.util.Currency


fun VariantEntity.toDomain(currency: String): Variant {
    val variant = Variant(
        id = this.id,
        sku = this.sku,
        price = Money.of(this.price, Currency.getInstance(currency)),
        quantity = this.quantity,
        attributes = this.attributes,
        deletedAt = this.deletedAt,
        deletedByParent = this.deletedByParent,
        productId = this.product.id
    )

    this.images.forEach { image ->
        variant.images.add(image.toDomain())
    }

    return variant
}

fun Variant.toEntity(productEntity: ProductEntity): VariantEntity {
    val entity = VariantEntity(
        id = this.id,
        sku = this.sku,
        price = this.price.amount,
        quantity = this.quantity,
        attributes = this.attributes,
        deletedAt = this.deletedAt,
        deletedByParent = this.deletedByParent,
        product = productEntity

    )

    this.images.forEach { image ->
        entity.images.add(image.toEntity(entity))
    }
    return entity
}

fun Variant.toResponse(): VariantResponse = VariantResponse(
    id = this.id,
    productId = this.productId,
    sku = this.sku,
    price = this.price.amount,
    currency = this.price.currency.currencyCode,
    quantity = this.quantity,
    attributes = this.attributes
)

fun VariantImage.toEntity(variantEntity: VariantEntity): VariantImageEntity  =  VariantImageEntity(
    id = this.id,
    url = this.url,
    originalName = this.originalName,
    storageKey = this.storageKey,
    sortOrder = this.sortOrder,
    variant = variantEntity,
)

fun VariantImageEntity.toDomain(): VariantImage = VariantImage(
    id = this.id,
    url = this.url,
    originalName = this.originalName,
    storageKey = this.storageKey,
    sortOrder = this.sortOrder,
    variantId = this.variant.id,
)
