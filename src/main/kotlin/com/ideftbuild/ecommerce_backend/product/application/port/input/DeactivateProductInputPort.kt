package com.ideftbuild.ecommerce_backend.product.application.port.input

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import java.util.UUID

interface DeactivateProductInputPort {
    fun execute(id: UUID): ProductResponse
}
