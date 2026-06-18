package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.user.api.dto.CreateRoleRequest
import com.ideftbuild.ecommerce_backend.user.api.dto.RoleResponse
import com.ideftbuild.ecommerce_backend.user.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.user.application.port.input.CreateRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.RoleOutputPort
import com.ideftbuild.ecommerce_backend.user.domain.Role
import org.springframework.stereotype.Service

@Service
class CreateRoleUseCase(
    private val roleOutputPort: RoleOutputPort
): CreateRoleInputPort {
    override fun execute(request: CreateRoleRequest): RoleResponse {
        val role = Role.create(
            request.name,
            request.description
        )

        return roleOutputPort.save(role).toResponse()
    }
}
