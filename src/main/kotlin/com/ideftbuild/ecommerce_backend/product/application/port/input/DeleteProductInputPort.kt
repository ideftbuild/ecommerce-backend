package com.ideftbuild.ecommerce_backend.product.application.port.input

import java.util.UUID

interface DeleteProductInputPort {
    fun execute(id: UUID)
}
