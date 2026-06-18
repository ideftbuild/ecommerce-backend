package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.application.port.input.AssignUserRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.RoleOutputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.UserOutputPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AssignUserRoleUseCase(
    private val roleOutputPort: RoleOutputPort,
    private val userOutputPort: UserOutputPort
): AssignUserRoleInputPort {
    override fun execute(userId: UUID, roleId: UUID) {
        val user = userOutputPort.findById(userId)
            ?: throw ResourceNotFoundException("user", userId)

        val role = roleOutputPort.findById(roleId)
            ?: throw ResourceNotFoundException("role", roleId)

        if (user.roles.contains(role))
            throw IllegalArgumentException("role already assigned")

        user.roles.add(role)

        userOutputPort.save(user)
    }
}
