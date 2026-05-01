package com.ideftbuild.ecommerce_backend.product.application.port.input

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.api.dto.UpdateProductRequest
import java.util.UUID

interface UpdateProductInputPort {
    fun execute(id: UUID, request: UpdateProductRequest): ProductResponse
}
