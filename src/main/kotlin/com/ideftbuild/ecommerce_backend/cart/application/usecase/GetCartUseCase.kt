package com.ideftbuild.ecommerce_backend.cart.application.usecase

import com.ideftbuild.ecommerce_backend.cart.api.dto.CartResponse
import com.ideftbuild.ecommerce_backend.cart.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.cart.application.port.input.GetCartInputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.output.CartOutputPort
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetCartUseCase (
    private val cartOutputPort: CartOutputPort,
): GetCartInputPort {
    override fun execute(userId: UUID): CartResponse {
        val cart = cartOutputPort.findByUserId(userId)
            ?: throw ResourceNotFoundException("Cart Owner", userId)

        return cart.toResponse(cart.getTotalItems(), cart.getTotalPrice())
    }
}
