package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.InvalidCredentialsException
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.shared.infrastructure.persistence.JwtService
import com.ideftbuild.ecommerce_backend.user.api.dto.AuthResponse
import com.ideftbuild.ecommerce_backend.user.api.dto.LoginRequest
import com.ideftbuild.ecommerce_backend.user.application.port.input.LoginUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.UserOutputPort
import com.ideftbuild.ecommerce_backend.user.domain.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginUserUseCase(
    private val userOutputPort: UserOutputPort,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder
): LoginUserInputPort {
    override fun execute(request: LoginRequest): AuthResponse {
        val identifier = request.username ?: request.email
        ?: throw InvalidCredentialsException()

        val user = userOutputPort.findByUsernameOrEmail(identifier)
            ?: throw InvalidCredentialsException()

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw InvalidCredentialsException()
        }

        return AuthResponse(jwtService.generateToken(
            user.username,
            user.roles.map { it.name }
        ))
    }
}
