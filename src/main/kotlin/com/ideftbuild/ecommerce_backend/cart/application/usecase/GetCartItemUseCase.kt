package com.ideftbuild.ecommerce_backend.cart.application.usecase

import com.ideftbuild.ecommerce_backend.cart.api.dto.CartItemResponse
import com.ideftbuild.ecommerce_backend.cart.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.cart.application.port.input.GetCartItemInputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.output.CartItemOutputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.output.CartOutputPort
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetCartItemUseCase(
    private val cartItemOutputPort: CartItemOutputPort,
    private val cartOutputPort: CartOutputPort
): GetCartItemInputPort {
    override fun execute(
        userId: UUID,
        itemId: UUID
    ): CartItemResponse {
        val cart = cartOutputPort.findByUserId(userId)
            ?: throw ResourceNotFoundException("Cart Owner", userId)

        // Ensure item is in cart
        val item = cart.findItem(itemId)
            ?: throw ResourceNotFoundException("CartItem", itemId)

        return item.toResponse()
    }
}
