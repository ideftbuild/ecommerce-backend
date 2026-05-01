package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper

import com.ideftbuild.ecommerce_backend.category.infrastructure.persistence.mapper.toDomain
import com.ideftbuild.ecommerce_backend.category.infrastructure.persistence.mapper.toEntity
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.ProductEntity
import com.ideftbuild.ecommerce_backend.product.domain.model.Money
import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import java.util.Currency

fun ProductEntity.toDomain(): Product {
    val product = Product(
        id = this.id,
        name = this.name,
        description = this.description,
        currency = Currency.getInstance(this.currency),
        status = this.status,
        minPrice = Money.of(this.minPrice, Currency.getInstance(this.currency)),
        maxPrice = Money.of(this.maxPrice, Currency.getInstance(this.currency)),
        category = this.category.toDomain(),
        updatedAt = this.updatedAt,
        createdAt = this.createdAt,
        deletedAt = this.deletedAt
    )
    this.variants.forEach {
        product.addVariant(it.toDomain(product.currency.currencyCode))
    }
    return product
}


fun Product.toEntity(): ProductEntity {
    val entity = ProductEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        currency = this.currency.currencyCode,
        minPrice = this.minPrice.amount,
        maxPrice = this.maxPrice.amount,
        status = this.status,
        category = this.category.toEntity(),
        deletedAt = this.deletedAt,
    ).apply {
        createdAt = this.createdAt
        updatedAt = this.updatedAt
    }
    this.variants.forEach { variant ->
        entity.variants.add(variant.toEntity(entity))
    }

    return entity
}
