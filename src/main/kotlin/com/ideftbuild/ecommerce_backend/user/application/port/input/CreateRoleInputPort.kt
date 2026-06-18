package com.ideftbuild.ecommerce_backend.user.application.port.input

import com.ideftbuild.ecommerce_backend.user.api.dto.CreateRoleRequest
import com.ideftbuild.ecommerce_backend.user.api.dto.RoleResponse

interface CreateRoleInputPort {
    fun execute(request: CreateRoleRequest): RoleResponse
}
