package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.application.port.input.UnAssignRolePermissionInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.PermissionOutPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.RoleOutputPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UnassignRolePermissionUseCase (
    private val roleOutputPort: RoleOutputPort,
    private val permissionOutputPort: PermissionOutPort
): UnAssignRolePermissionInputPort {
    override fun execute(roleId: UUID, permissionId: UUID) {
        val role = roleOutputPort.findById(roleId)
            ?: throw ResourceNotFoundException("role", roleId)

        val permission = permissionOutputPort.findById(permissionId)
            ?: throw ResourceNotFoundException("permission", permissionId)

        if (!role.permissions.contains(permission))
            throw IllegalArgumentException("permission not assigned")

        role.permissions.remove(permission)

        roleOutputPort.save(role)
    }
}
