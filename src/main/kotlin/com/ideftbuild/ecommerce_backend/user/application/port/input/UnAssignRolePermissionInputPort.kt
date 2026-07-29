package com.ideftbuild.ecommerce_backend.user.application.port.input

import java.util.UUID

interface UnAssignRolePermissionInputPort {
    fun execute(roleId: UUID, permissionId: UUID)
}
