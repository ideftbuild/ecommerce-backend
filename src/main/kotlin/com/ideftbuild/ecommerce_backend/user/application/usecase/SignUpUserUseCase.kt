package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.user.api.dto.SignUpRequest
import com.ideftbuild.ecommerce_backend.user.api.dto.UserResponse
import com.ideftbuild.ecommerce_backend.user.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.user.application.port.input.SignUpUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.UserOutputPort
import com.ideftbuild.ecommerce_backend.user.domain.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignUpUserUseCase (
    private val userOutputPort: UserOutputPort,
    private val passwordEncoder: PasswordEncoder
): SignUpUserInputPort {
    override fun execute(request: SignUpRequest): UserResponse {
        val password = passwordEncoder.encode(request.password)
            ?: throw IllegalArgumentException("Password cannot be null")

        val user = User.create(
            username = request.username,
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            password = password
        )

        return userOutputPort.save(user).toResponse()
    }

}
