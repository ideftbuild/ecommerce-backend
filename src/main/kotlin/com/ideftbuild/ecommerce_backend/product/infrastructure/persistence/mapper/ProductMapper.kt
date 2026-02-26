package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper

import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.ProductEntity
import com.ideftbuild.ecommerce_backend.product.domain.model.Money
import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import org.springframework.stereotype.Component
import java.util.Currency

@Component
class ProductMapper {

    fun toDomain(entity: ProductEntity): Product {
        return Product(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            price = Money.of(entity.price, Currency.getInstance(entity.currency)),
            quantity = entity.quantity,
            updatedAt = entity.updatedAt,
            createdAt = entity.createdAt,
            deletedAt = entity.deletedAt
        )
    }

    fun toEntity(product: Product): ProductEntity {
        return ProductEntity(
            id = product.id,
            name = product.name,
            description = product.description,
            price = product.price.amount,
            currency = product.price.currency.currencyCode,
            quantity = product.quantity,
            status = product.status,
            deletedAt = product.deletedAt,
        ).apply {
            createdAt = product.createdAt
            updatedAt = product.updatedAt
        }
    }
}
