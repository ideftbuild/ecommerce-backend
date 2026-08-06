package com.ideftbuild.ecommerce_backend.cart.api

import com.ideftbuild.ecommerce_backend.cart.api.dto.AddItemRequest
import com.ideftbuild.ecommerce_backend.cart.api.dto.CartItemResponse
import com.ideftbuild.ecommerce_backend.cart.api.dto.CartResponse
import com.ideftbuild.ecommerce_backend.cart.application.port.input.AddCartItemInputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.input.DeleteCartInputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.input.GetCartInputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.input.GetCartItemInputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.input.RemoveCartItemInputPort
import com.ideftbuild.ecommerce_backend.cart.application.port.input.UpdateCartItemQuantityInputPort
import com.ideftbuild.ecommerce_backend.user.api.dto.UserResponse
import com.ideftbuild.ecommerce_backend.user.domain.CustomUserDetails
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/cart")
class CartController (
    private val get: GetCartInputPort,
    private val delete: DeleteCartInputPort,
    private val getItem: GetCartItemInputPort,
    private val addItem: AddCartItemInputPort,
    private val removeItem: RemoveCartItemInputPort,
    private val updateItemQuantity: UpdateCartItemQuantityInputPort,
){
    @Operation(
        summary = "Retrieve user's cart",
        description = "Fetches current user cart"
    )
    @GetMapping("/")
    fun get(@AuthenticationPrincipal user: CustomUserDetails): ResponseEntity<CartResponse> {
        return ResponseEntity.ok(get.execute(user.id))
    }

    @Operation(
        summary = "Add cart item",
        description = "Add new cart item to current user's cart"
    )
    @PostMapping("/")
    fun addItem(
        @AuthenticationPrincipal user: CustomUserDetails,
        @Valid @RequestBody addItemRequest: AddItemRequest): ResponseEntity<CartResponse> {
        return ResponseEntity.ok(addItem.execute(
            user.id,
            variantId = addItemRequest.variantId,
            quantity = addItemRequest.quantity
        ))
    }

    @Operation(
        summary = "Retrieve a user's cart item",
        description = "Fetches current user cart item by id"
    )
    @GetMapping("/items/{itemId}")
    fun getItem(@AuthenticationPrincipal user: CustomUserDetails, @PathVariable itemId: UUID): ResponseEntity<CartItemResponse> {
        return ResponseEntity.ok(getItem.execute(user.id, itemId))
    }

    @Operation(
        summary = "Update user's cart item quantity",
        description = "Update current user cart item quantity using by id"
    )
    @PatchMapping("/items/{itemId}")
    fun updateItemQuantity(
        @AuthenticationPrincipal user: CustomUserDetails,
        @PathVariable itemId: UUID,
        @RequestBody quantity: Int
    ): ResponseEntity<CartItemResponse> {
        return ResponseEntity.ok(updateItemQuantity.execute(user.id, itemId, quantity))
    }

    @Operation(
        summary = "Remove cart item",
        description = "Remove current user cart item by id"
    )
    @DeleteMapping("/items/{itemId}")
    fun removeItem(@AuthenticationPrincipal user: CustomUserDetails, @PathVariable itemId: UUID): ResponseEntity<Void> {
        removeItem.execute(user.id, itemId)
        return ResponseEntity.noContent().build()
    }

    @Operation(
        summary = "Delete User's Cart",
        description = "Delete a user cart. Note this action is irreversible"
    )
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/cart/{cartId}")
    fun delete(@PathVariable cartId: UUID): ResponseEntity<Void> {
        delete.execute(cartId)
        return ResponseEntity.noContent().build()
    }
}
