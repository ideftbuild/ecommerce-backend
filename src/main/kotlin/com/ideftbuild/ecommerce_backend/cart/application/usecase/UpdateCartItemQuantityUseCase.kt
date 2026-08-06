package com.ideftbuild.ecommerce_backend.cart.application.usecase

import com.ideftbuild.ecommerce_backend.cart.api.dto.CartItemResponse
import com.ideftbuild.ecommerce_backend.cart.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.cart.application.port.input.UpdateCartItemQuantityInputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.output.CartItemOutputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.output.CartOutputPort
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UpdateCartItemQuantityUseCase(
    private val cartItemOutputPort: CartItemOutputPort,
    private val cartOutputPort: CartOutputPort
): UpdateCartItemQuantityInputPort {
    override fun execute(
        userId: UUID,
        itemId: UUID,
        quantity: Int
    ): CartItemResponse {
        if (quantity == 0) throw IllegalArgumentException(
            "Quantity must be greater than zero. Use remove item action to delete this cart item.")

        // Ensure item exists
        val cart = cartOutputPort.findByUserId(userId)
            ?: throw ResourceNotFoundException("Cart Owner", userId)

        // Ensure item is in cart
        var item = cart.findItem(itemId)
            ?: throw ResourceNotFoundException("CartItem", itemId)

        item.quantity = quantity

        item = cartItemOutputPort.save(item)

        return item.toResponse()
    }
}
