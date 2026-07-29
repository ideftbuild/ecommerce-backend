package com.ideftbuild.ecommerce_backend.user.api

import com.ideftbuild.ecommerce_backend.user.api.dto.UpdateUserRequest
import com.ideftbuild.ecommerce_backend.user.api.dto.UserResponse
import com.ideftbuild.ecommerce_backend.user.application.CustomUserDetailsService
import com.ideftbuild.ecommerce_backend.user.application.port.input.DeleteUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetAllUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetMeInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.RestoreUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.UpdateUserInputPort
import com.ideftbuild.ecommerce_backend.user.domain.CustomUserDetails
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val get: GetUserInputPort,
    private val getAll: GetAllUserInputPort,
    private val update: UpdateUserInputPort,
    private val delete: DeleteUserInputPort,
    private val restore: RestoreUserInputPort
) {

    @Operation(
        summary = "Get current authenticated user",
        description = "Get current user logged in"
    )
    @GetMapping("/me")
    fun getLoggedInUser(@AuthenticationPrincipal user: CustomUserDetails): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(get.execute(user.id))
    }

    @Operation(
        summary = "Update current authenticated user",
        description = "Update the currently logged in user"
    )
    @PutMapping("/me")
    fun updateLoggedInUser(@AuthenticationPrincipal user: CustomUserDetails, @RequestBody request: UpdateUserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(update.execute(user.id, request))
    }

    @Operation(
        summary = "Delete current authenticated user",
        description = "Delete the currently logged in user"
    )
    @DeleteMapping("/me")
    fun deleteLoggedInUser(@AuthenticationPrincipal user: CustomUserDetails): ResponseEntity<Void> {
        delete.execute(user.id)
        return ResponseEntity.noContent().build()
    }

    @Operation(
        summary = "Get Users",
        description = "Get All Registered Users"
    )
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("")
    fun getAll(): ResponseEntity<List<UserResponse>> {
        return ResponseEntity.ok(getAll.execute())
    }

    @Operation(
        summary = "Get User",
        description = "Get User"
    )
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(get.execute(id))
    }


    @Operation(
        summary = "Update User",
        description = "Update registered User"
    )
    @PreAuthorize("hasAuthority('user:update')")
    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody request: UpdateUserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(update.execute(id, request))
    }


    @Operation(
        summary = "Delete User",
        description = "Delete registered User"
    )
    @PreAuthorize("hasAuthority('user:delete')")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Void> {
        delete.execute(id)
        return ResponseEntity.noContent().build()
    }

    @Operation(
        summary = "Restore User",
        description = "Restore deleted User"
    )
    @PreAuthorize("hasAuthority('user:restore')")
    @PatchMapping("/{id}/restore")
    fun restore(@PathVariable id: UUID): ResponseEntity<Void> {
        restore.execute(id)
        return ResponseEntity.noContent().build()
    }
}
