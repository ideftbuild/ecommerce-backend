package com.ideftbuild.ecommerce_backend.user.application.port.input

import com.ideftbuild.ecommerce_backend.user.api.dto.RoleResponse
import com.ideftbuild.ecommerce_backend.user.api.dto.UpdateRoleRequest
import java.util.UUID

interface UpdateRoleInputPort {
    fun execute(id: UUID, request: UpdateRoleRequest): RoleResponse
}
