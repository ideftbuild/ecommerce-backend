package com.ideftbuild.ecommerce_backend.cart.application.usecase

import com.ideftbuild.ecommerce_backend.cart.application.port.input.DeleteCartInputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.output.CartOutputPort
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeleteCartUseCase(
    private val cartOutputPort: CartOutputPort,
): DeleteCartInputPort {
    override fun execute(cartId: UUID) {
        if (!cartOutputPort.existsById(cartId)) {
            throw ResourceNotFoundException("Cart", cartId)
        }

        cartOutputPort.deleteById(cartId)
    }
}
