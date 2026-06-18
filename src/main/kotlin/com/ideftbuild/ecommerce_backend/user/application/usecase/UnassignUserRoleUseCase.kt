package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.application.port.input.UnAssignUserRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.RoleOutputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.UserOutputPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UnassignUserRoleUseCase(
    private val roleOutputPort: RoleOutputPort,
    private val userOutputPort: UserOutputPort
): UnAssignUserRoleInputPort {
    override fun execute(userId: UUID, roleId: UUID) {
        val user = userOutputPort.findById(userId)
            ?: throw ResourceNotFoundException("user", userId)

        val role = roleOutputPort.findById(roleId)
            ?: throw ResourceNotFoundException("role", roleId)

        if (!user.roles.contains(role))
            throw IllegalArgumentException("role not assigned")

        user.roles.remove(role)

        userOutputPort.save(user)
    }
}
