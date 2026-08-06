package com.ideftbuild.ecommerce_backend.cart.application.port.input

import com.ideftbuild.ecommerce_backend.cart.api.dto.CartResponse
import java.util.UUID

interface GetCartInputPort {
    fun execute(userId: UUID): CartResponse
}
