package com.ideftbuild.ecommerce_backend.user.application.port.input

import java.util.UUID

interface UnAssignUserRoleInputPort {
    fun execute(userId: UUID, roleId: UUID)
}
