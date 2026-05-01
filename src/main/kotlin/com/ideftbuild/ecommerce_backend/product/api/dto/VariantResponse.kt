package com.ideftbuild.ecommerce_backend.product.api.dto

import com.ideftbuild.ecommerce_backend.product.domain.model.Variant
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal
import java.util.UUID

class VariantResponse (
    val id: UUID?,

    val productId: UUID?,

    val sku: String,

    val price: BigDecimal,

    val currency: String,

    val quantity: Long,

    val attributes: Map<String, Any> = mapOf()
): RepresentationModel<VariantResponse>() {

    companion object {
        fun from(variant: Variant): VariantResponse {
            return VariantResponse(
                id = variant.id,
                productId = variant.productId,
                sku = variant.sku,
                price = variant.price.amount,
                currency = variant.price.currency.currencyCode,
                quantity = variant.quantity,
                attributes = variant.attributes
            )
        }
    }
}
