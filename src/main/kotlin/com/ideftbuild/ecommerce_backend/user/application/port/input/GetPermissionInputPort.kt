package com.ideftbuild.ecommerce_backend.user.application.port.input

import com.ideftbuild.ecommerce_backend.user.api.dto.PermissionResponse
import com.ideftbuild.ecommerce_backend.user.domain.Permission
import java.util.UUID

interface GetPermissionInputPort {
    fun execute(id: UUID): PermissionResponse
}
