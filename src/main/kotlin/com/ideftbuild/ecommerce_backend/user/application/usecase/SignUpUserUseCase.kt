package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.api.dto.SignUpRequest
import com.ideftbuild.ecommerce_backend.user.api.dto.UserResponse
import com.ideftbuild.ecommerce_backend.user.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetRoleInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.SignUpUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.RoleOutputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.UserOutputPort
import com.ideftbuild.ecommerce_backend.user.domain.User
import com.ideftbuild.ecommerce_backend.user.exception.UserCreationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

val DEFAULT_USER = "USER"

@Service
class SignUpUserUseCase (
    private val userOutputPort: UserOutputPort,
    private val passwordEncoder: PasswordEncoder,
    private val roleOutputPort: RoleOutputPort
): SignUpUserInputPort {
    override fun execute(request: SignUpRequest): UserResponse {
        val password = passwordEncoder.encode(request.password)
            ?: throw IllegalArgumentException("Password cannot be null")

        var user = User.create(
            username = request.username,
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            password = password
        )

        try {
            // assign default role
            val role = roleOutputPort.findByName(DEFAULT_USER)
                ?: throw ResourceNotFoundException("role", message = DEFAULT_USER)

            user.roles.add(role)

            user = userOutputPort.save(user)
        } catch (ex: Exception) {
            throw UserCreationException("Failed to create user.", ex)
        }

        return user.toResponse()
    }

}
