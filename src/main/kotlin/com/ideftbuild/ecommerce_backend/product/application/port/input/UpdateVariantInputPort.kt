package com.ideftbuild.ecommerce_backend.product.application.port.input

import com.ideftbuild.ecommerce_backend.product.api.dto.UpdateVariantRequest
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import java.util.UUID

interface UpdateVariantInputPort {
    fun execute(variantId: UUID, request: UpdateVariantRequest): VariantResponse
}
