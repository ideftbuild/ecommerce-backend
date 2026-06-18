package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.api.dto.RoleResponse
import com.ideftbuild.ecommerce_backend.user.api.dto.UpdateRoleRequest
import com.ideftbuild.ecommerce_backend.user.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.user.application.port.input.UpdateRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.RoleOutputPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UpdateRoleUseCase(
    private val roleOutputPort: RoleOutputPort
): UpdateRoleInputPort {
    override fun execute(
        id: UUID,
        request: UpdateRoleRequest
    ): RoleResponse {
        val role = roleOutputPort.findById(id)
            ?: throw ResourceNotFoundException("role", id)

        role.update(request.description)

        return roleOutputPort.save(role).toResponse()
    }
}
