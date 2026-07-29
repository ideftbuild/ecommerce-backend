package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.application.port.input.AssignRolePermissionInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.PermissionOutPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.RoleOutputPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AssignRolePermissionUseCase(
    private val roleOutputPort: RoleOutputPort,
    private val permissionOutPort: PermissionOutPort
): AssignRolePermissionInputPort {
    override fun execute(roleId: UUID, permissionId: UUID) {
        val role = roleOutputPort.findById(roleId)
            ?: throw ResourceNotFoundException("role", roleId)
        println("role is ${role.name}")

        val permission = permissionOutPort.findById(permissionId)
            ?: throw ResourceNotFoundException("permission", permissionId)

        println("permission is ${permission.name}")

        if (role.permissions.contains(permission))
            throw IllegalArgumentException("permission already assigned")

        role.permissions.add(permission)
        println("Added permission to role")

        roleOutputPort.save(role)
        println("saving role")
    }
}
