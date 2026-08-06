package com.ideftbuild.ecommerce_backend.cart.application.port.output

import com.ideftbuild.ecommerce_backend.cart.domain.Cart
import java.util.UUID

interface CartOutputPort {
    fun save(cart: Cart): Cart

    fun findById(id: UUID): Cart?


    fun deleteById(id: UUID)

    fun existsById(id: UUID): Boolean

    fun findByUserId(id: UUID): Cart?
}
