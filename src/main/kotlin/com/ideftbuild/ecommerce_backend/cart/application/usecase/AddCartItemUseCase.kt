package com.ideftbuild.ecommerce_backend.cart.application.usecase

import com.ideftbuild.ecommerce_backend.cart.api.dto.CartResponse
import com.ideftbuild.ecommerce_backend.cart.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.cart.application.port.input.AddCartItemInputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.output.CartOutputPort
import com.ideftbuild.ecommerce_backend.cart.domain.Cart
import com.ideftbuild.ecommerce_backend.cart.domain.CartItem
import com.ideftbuild.ecommerce_backend.product.application.port.output.VariantOutputPort
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AddCartItemUseCase(
    private val cartOutputPort: CartOutputPort,
    private val variantOutputPort: VariantOutputPort
): AddCartItemInputPort {
    override fun execute(
        userId: UUID,
        variantId: UUID,
        quantity: Int
    ): CartResponse {
        var cart = cartOutputPort.findByUserId(userId)

        // create cart if it doesn't exist
        if (cart == null) {
            cart = cartOutputPort.save(Cart.create(userId))
        }

        val variant = variantOutputPort.findById(variantId)
            ?: throw ResourceNotFoundException("variant", variantId)

        // Ensure the requested quantity does not exceed the available stock for the selected variant.
        if (quantity > variant.quantity) {
            throw IllegalArgumentException(
                "Requested quantity ($quantity) exceeds the available stock (${variant.quantity}) for this product variant."
            )
        }
        var item = cart.findItemByVariantId(variantId)

        if (item != null){
            // update existing item quantity if available
            item.quantity = quantity
        } else {
            item = CartItem.create(
                cartId = cart.id!!,
                variant = variant,
                quantity = quantity,
            )
            cart = cart.addItem(item)
        }

        cart = cartOutputPort.save(cart)

        return cart.toResponse(cart.getTotalItems(), cart.getTotalPrice())
    }
}
