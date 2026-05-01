package com.ideftbuild.ecommerce_backend.product.application.port.input

import com.ideftbuild.ecommerce_backend.product.api.dto.CreateVariantRequest
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import java.util.UUID

interface AddVariantInputPort {
    fun execute(productId: UUID, request: CreateVariantRequest): VariantResponse
}
