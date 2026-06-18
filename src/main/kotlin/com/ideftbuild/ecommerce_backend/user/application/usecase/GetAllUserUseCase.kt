package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.user.api.dto.UserResponse
import com.ideftbuild.ecommerce_backend.user.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetAllUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.UserOutputPort
import org.springframework.stereotype.Service

@Service
class GetAllUserUseCase(
    private val userOutputPort: UserOutputPort
): GetAllUserInputPort {
    override fun execute(): List<UserResponse> {
        return userOutputPort.findAll().map { it.toResponse() }
    }
}
