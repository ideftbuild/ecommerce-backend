package com.ideftbuild.ecommerce_backend.product.application.port.input

import java.util.UUID

interface DeleteImageInputPort {
    fun execute(variantId: UUID, name: String)
}
