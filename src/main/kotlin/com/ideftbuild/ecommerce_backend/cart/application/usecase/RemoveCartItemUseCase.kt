package com.ideftbuild.ecommerce_backend.cart.application.usecase

import com.ideftbuild.ecommerce_backend.cart.application.port.input.RemoveCartItemInputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.output.CartItemOutputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.output.CartOutputPort
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RemoveCartItemUseCase (
    private val cartOutputPort: CartOutputPort
): RemoveCartItemInputPort  {
    override fun execute(userId: UUID, itemId: UUID) {
        // Ensure item exists
        val cart = cartOutputPort.findByUserId(userId)
            ?: throw ResourceNotFoundException("Cart Owner", userId)

        if (!cart.deleteItem(itemId)) {
            throw ResourceNotFoundException("CartItem", itemId)
        }

        cartOutputPort.save(cart)
    }
}
