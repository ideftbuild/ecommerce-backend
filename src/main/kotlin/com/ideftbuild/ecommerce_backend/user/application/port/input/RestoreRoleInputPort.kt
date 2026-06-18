package com.ideftbuild.ecommerce_backend.user.application.port.input

import com.ideftbuild.ecommerce_backend.user.api.dto.RoleResponse
import java.util.UUID

interface RestoreRoleInputPort {
    fun execute(id: UUID): RoleResponse
}
