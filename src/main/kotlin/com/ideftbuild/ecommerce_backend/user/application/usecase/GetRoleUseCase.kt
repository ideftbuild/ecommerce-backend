package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.api.dto.RoleResponse
import com.ideftbuild.ecommerce_backend.user.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.RoleOutputPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetRoleUseCase(
    private val roleOutputPort: RoleOutputPort
): GetRoleInputPort {
    override fun execute(id: UUID): RoleResponse {
        return roleOutputPort.findById(id)?.toResponse()
            ?: throw ResourceNotFoundException("role", id)
    }

    override fun execute(name: String): RoleResponse {
        return roleOutputPort.findByName(name)?.toResponse()
            ?: throw ResourceNotFoundException("role", message = "name")
    }
}
