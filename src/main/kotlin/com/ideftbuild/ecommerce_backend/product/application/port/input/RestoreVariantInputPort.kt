package com.ideftbuild.ecommerce_backend.product.application.port.input

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import java.util.UUID

interface RestoreVariantInputPort {
    fun execute(productId: UUID, variantId: UUID): VariantResponse
}
