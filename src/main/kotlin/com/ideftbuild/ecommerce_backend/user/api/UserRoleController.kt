package com.ideftbuild.ecommerce_backend.user.api

import com.ideftbuild.ecommerce_backend.user.application.port.input.AssignUserRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.UnAssignUserRoleInputPort
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("/api/v1/users")
class UserRoleController(
    private val assign: AssignUserRoleInputPort,
    private val unassign: UnAssignUserRoleInputPort
) {

    @Operation(
        summary = "Assign role",
        description = "Assign a role to user"
    )
    @PutMapping("/{userId}/roles/{roleId}")
    fun assign(@PathVariable userId: UUID, @PathVariable roleId: UUID): ResponseEntity<Void> {
        assign.execute(userId, roleId)
        return ResponseEntity.noContent().build()
    }

    @Operation(
        summary = "Unassign role",
        description = "unassign a role"
    )
    @DeleteMapping("/{userId}/roles/{roleId}")
    fun unassign(@PathVariable userId: UUID, @PathVariable roleId: UUID): ResponseEntity<Void> {
        unassign.execute(userId, roleId)
        return ResponseEntity.noContent().build()
    }
}
