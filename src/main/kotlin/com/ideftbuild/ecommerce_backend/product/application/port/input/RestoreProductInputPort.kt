package com.ideftbuild.ecommerce_backend.product.application.port.input

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import java.util.UUID

interface RestoreProductInputPort {
    fun execute(id: UUID): ProductResponse
}
