package com.ideftbuild.ecommerce_backend.user.api

import com.ideftbuild.ecommerce_backend.user.application.port.input.AssignRolePermissionInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.UnAssignRolePermissionInputPort
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID



@RestController
@RequestMapping("/api/v1/roles")
class RolePermissionController (
    private val assign: AssignRolePermissionInputPort,
    private val unassign: UnAssignRolePermissionInputPort
){

    @Operation(
        summary = "Assign permission",
        description = "Assign a permission to role"
    )
    @PreAuthorize("hasAuthority('permission:assign')")
    @PutMapping("/{roleId}/permissions/{permissionId}")
    fun assign(@PathVariable roleId: UUID, @PathVariable permissionId: UUID): ResponseEntity<Void> {
        assign.execute(roleId, permissionId)
        return ResponseEntity.noContent().build()
    }

    @Operation(
        summary = "Unassign permission",
        description = "unassign a permission"
    )
    @PreAuthorize("hasAuthority('permission:unassign')")
    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    fun unassign(@PathVariable roleId: UUID, @PathVariable permissionId: UUID): ResponseEntity<Void> {
        unassign.execute(roleId, permissionId)
        return ResponseEntity.noContent().build()
    }
}
