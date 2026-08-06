package com.ideftbuild.ecommerce_backend.cart.application.port.input

import java.util.UUID

interface DeleteCartInputPort {
    fun execute(cartId: UUID)
}
