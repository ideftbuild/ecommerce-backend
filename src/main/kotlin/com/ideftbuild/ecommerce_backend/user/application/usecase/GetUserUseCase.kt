package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.api.dto.UserResponse
import com.ideftbuild.ecommerce_backend.user.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.UserOutputPort
import org.osgi.resource.Resource
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetUserUseCase(
    private val userOutputPort: UserOutputPort
): GetUserInputPort {

    override fun execute(id: UUID): UserResponse {
        val user = userOutputPort.findById(id)
            ?: throw ResourceNotFoundException("user", id)

        return user.toResponse()
    }

    override fun execute(username: String): UserResponse {
        val user = userOutputPort.findByUsername(username)
            ?: throw ResourceNotFoundException("user", message = username)

        return user.toResponse()
    }
}
