package com.ideftbuild.ecommerce_backend.user.api

import com.ideftbuild.ecommerce_backend.user.api.dto.AuthResponse
import com.ideftbuild.ecommerce_backend.user.api.dto.LoginRequest
import com.ideftbuild.ecommerce_backend.user.api.dto.SignUpRequest
import com.ideftbuild.ecommerce_backend.user.api.dto.UserResponse
import com.ideftbuild.ecommerce_backend.user.application.port.input.LoginUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.input.SignUpUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.usecase.LoginUserUseCase
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController (
    private val login: LoginUserInputPort,
    private val signUp: SignUpUserInputPort
) {


    @Operation(
        summary = "Login",
        description = "Authenticate users and return token"
    )
    @PostMapping
        ("/login")
    fun login(@RequestBody @Valid request: LoginRequest): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(login.execute(request))
    }

    @Operation(
        summary = "Sign up",
        description = "Create a new user account"
    )
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid request: SignUpRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(signUp.execute(request))
    }
}
