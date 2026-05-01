package com.ideftbuild.ecommerce_backend.product.api.dto

import com.ideftbuild.ecommerce_backend.product.domain.model.VariantImage
import java.util.UUID

class VariantImageResponse (
    val id: UUID?,

    val url: String,

    val originalName: String,

    val storageKey: String,

    val sortOrder: Int,

    val variantId: UUID?
) {
    companion object {
        fun from(
            variantImage: VariantImage
        ): VariantImageResponse {
            return VariantImageResponse(
                id = variantImage.id,
                url = variantImage.url,
                originalName = variantImage.originalName,
                storageKey = variantImage.storageKey,
                sortOrder = variantImage.sortOrder,
                variantId = variantImage.variantId
            )
        }
    }
}
