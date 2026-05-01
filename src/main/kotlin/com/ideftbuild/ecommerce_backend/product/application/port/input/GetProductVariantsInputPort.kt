package com.ideftbuild.ecommerce_backend.product.application.port.input

import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import java.util.UUID

interface GetProductVariantsInputPort {
    fun execute(productId: UUID): List<VariantResponse>
}
