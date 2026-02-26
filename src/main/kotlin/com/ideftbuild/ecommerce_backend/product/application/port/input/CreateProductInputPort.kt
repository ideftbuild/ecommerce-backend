package com.ideftbuild.ecommerce_backend.product.application.port.input

import com.ideftbuild.ecommerce_backend.product.api.dto.CreateProductRequest
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse

interface CreateProductInputPort {
    fun execute(request: CreateProductRequest): ProductResponse
}
