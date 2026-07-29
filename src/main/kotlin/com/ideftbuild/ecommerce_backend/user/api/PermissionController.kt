package com.ideftbuild.ecommerce_backend.user.api

import com.ideftbuild.ecommerce_backend.user.api.dto.PermissionResponse
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetAllPermissionsInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetPermissionInputPort
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController

@RequestMapping("/api/v1/permissions")
class PermissionController(
    private val getAll: GetAllPermissionsInputPort,
    private val get: GetPermissionInputPort
) {


    @Operation(
        summary = "Get Permission",
        description = "Get a Permission"
    )
    @PreAuthorize("hasAuthority('permission:read')")
    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): ResponseEntity<PermissionResponse> {
        return ResponseEntity.ok(get.execute(id))
    }

    @Operation(
        summary = "Get Permissions",
        description = "Get all permissions"
    )
    @PreAuthorize("hasAuthority('permission:read')")
    @GetMapping("")
    fun getAll(): ResponseEntity<List<PermissionResponse>> {
        return ResponseEntity.ok(getAll.execute())
    }
}
