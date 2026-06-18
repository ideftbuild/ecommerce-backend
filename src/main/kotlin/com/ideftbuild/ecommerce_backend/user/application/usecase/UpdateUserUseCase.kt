package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.api.dto.UpdateUserRequest
import com.ideftbuild.ecommerce_backend.user.api.dto.UserResponse
import com.ideftbuild.ecommerce_backend.user.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.user.application.port.input.UpdateUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.UserOutputPort
import com.ideftbuild.ecommerce_backend.user.domain.User
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UpdateUserUseCase(
  private val userOutputPort: UserOutputPort
): UpdateUserInputPort {
    private fun updateUser(user: User, request: UpdateUserRequest): User {
        user.update(
            request.username,
            request.lastName,
            request.firstName
        )

        return userOutputPort.save(user)
    }
    override fun execute(
        id: UUID,
        request: UpdateUserRequest
    ): UserResponse {
        val user = userOutputPort.findById(id)
            ?: throw ResourceNotFoundException("user", id)

        return updateUser(user, request).toResponse()
    }

    override fun execute(
        username: String,
        request: UpdateUserRequest
    ): UserResponse {
        val user = userOutputPort.findByUsername(username)
            ?: throw ResourceNotFoundException("user", message = username)

        return updateUser(user, request).toResponse()
    }
}
