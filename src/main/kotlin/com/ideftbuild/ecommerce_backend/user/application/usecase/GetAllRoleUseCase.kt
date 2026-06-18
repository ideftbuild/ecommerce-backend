package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.user.api.dto.RoleResponse
import com.ideftbuild.ecommerce_backend.user.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetAllRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.RoleOutputPort
import org.springframework.stereotype.Service

@Service
class GetAllRoleUseCase(
    private val roleOutputPort: RoleOutputPort
): GetAllRoleInputPort {
    override fun execute(): List<RoleResponse> {
        return roleOutputPort.findAll().map { it.toResponse() }
    }
}
