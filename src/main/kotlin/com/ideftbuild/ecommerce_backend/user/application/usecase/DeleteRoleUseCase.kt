package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.application.port.input.DeleteRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.RoleOutputPort
import com.ideftbuild.ecommerce_backend.user.domain.Role
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeleteRoleUseCase(
    private val roleOutputPort: RoleOutputPort
): DeleteRoleInputPort {
    private fun delete(role: Role) {
        role.softDelete()

        roleOutputPort.save(role)
    }

    override fun execute(id: UUID) {
        val role = roleOutputPort.findById(id)
            ?: throw ResourceNotFoundException("role", id)
        delete(role)
    }

    override fun execute(name: String) {
        val role = roleOutputPort.findByName(name)
            ?: throw ResourceNotFoundException("role", message = name)

        delete(role)
    }
}
