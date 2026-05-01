package com.ideftbuild.ecommerce_backend.product.application.port.input

import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.domain.model.Variant

interface  GetVariantBySkuInputPort {
    fun execute(sku: String): VariantResponse
}
