package com.ideftbuild.ecommerce_backend.user.application.port.input

import java.util.UUID


interface AssignRolePermissionInputPort {
    fun execute(roleId: UUID, permissionId: UUID)
}
