package com.ideftbuild.ecommerce_backend.user.application.port.input

import com.ideftbuild.ecommerce_backend.user.api.dto.RoleResponse

interface GetAllRoleInputPort {
    fun execute(): List<RoleResponse>
}
