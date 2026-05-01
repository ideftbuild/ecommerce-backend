package com.ideftbuild.ecommerce_backend.product.api.mapper

import com.ideftbuild.ecommerce_backend.product.api.dto.CreateVariantRequest
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantImageResponse
import com.ideftbuild.ecommerce_backend.product.domain.model.Money
import com.ideftbuild.ecommerce_backend.product.domain.model.Variant
import com.ideftbuild.ecommerce_backend.product.domain.model.VariantImage
import com.ideftbuild.ecommerce_backend.shared.utils.generateSku
import java.util.Currency
import java.util.UUID

fun CreateVariantRequest.toVariant(productId: UUID? = null, currency: Currency): Variant {
    return Variant(
        sku = sku ?: generateSku(
            this.attributes?.values
                ?.take(2)
                ?.joinToString("-") {
                    it.toString().uppercase().replace(" ", "")
                } ?: "VAR"
        ),
        price = Money.of(this.price, currency),
        attributes = this.attributes!!,
        quantity = this.quantity,
        productId = productId,
    )
}

    fun VariantImage.toVariantImageResponse(): VariantImageResponse = VariantImageResponse(
        id = this.id,
        url = this.url,
        originalName = this.originalName,
        storageKey = this.storageKey,
        sortOrder = this.sortOrder,
        variantId = variantId
    )
