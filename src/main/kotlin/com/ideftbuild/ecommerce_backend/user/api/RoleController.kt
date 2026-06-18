package com.ideftbuild.ecommerce_backend.user.api

import com.ideftbuild.ecommerce_backend.user.api.dto.CreateRoleRequest
import com.ideftbuild.ecommerce_backend.user.api.dto.RoleResponse
import com.ideftbuild.ecommerce_backend.user.api.dto.UpdateRoleRequest
import com.ideftbuild.ecommerce_backend.user.application.port.input.CreateRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.DeleteRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetAllRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.RestoreRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.UpdateRoleInputPort
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.repository.RoleRepository
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import kotlin.uuid.Uuid

@RestController

@RequestMapping("/api/v1/roles")
class RoleController(
    private val create: CreateRoleInputPort,
    private val delete: DeleteRoleInputPort,
    private val update: UpdateRoleInputPort,
    private val restore: RestoreRoleInputPort,
    private val get: GetRoleInputPort,
    private val getAll: GetAllRoleInputPort
) {

    @Operation(
        summary = "Create role",
        description = "Create a role"
    )
    @PostMapping("")
    fun create(@RequestBody request: CreateRoleRequest): ResponseEntity<RoleResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(create.execute(request))
    }


    @Operation(
        summary = "Get role",
        description = "Get a role"
    )
    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): ResponseEntity<RoleResponse> {
        return ResponseEntity.ok(get.execute(id))
    }


    @Operation(
        summary = "Get roles",
        description = "Get all role"
    )
    @GetMapping("")
    fun getAll(): ResponseEntity<List<RoleResponse>> {
        return ResponseEntity.ok(getAll.execute())
    }


    @Operation(
        summary = "Delete role",
        description = "Delete a role"
    )
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Void> {
        delete.execute(id)
        return ResponseEntity.noContent().build()
    }

    @Operation(
        summary = "Restore role",
        description = "Restore role"
    )
    @PatchMapping("/{id}/restore")
    fun restore(@PathVariable id: UUID): ResponseEntity<RoleResponse> {
        return ResponseEntity.ok(restore.execute(id))
    }

    @Operation(
        summary = "Update role",
        description = "Update role description"
    )
    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody request: UpdateRoleRequest): ResponseEntity<RoleResponse> {
        return ResponseEntity.ok(update.execute(id, request))
    }
}
