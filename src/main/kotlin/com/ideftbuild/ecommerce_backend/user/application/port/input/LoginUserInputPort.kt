package com.ideftbuild.ecommerce_backend.user.application.port.input

import com.ideftbuild.ecommerce_backend.user.api.dto.AuthResponse
import com.ideftbuild.ecommerce_backend.user.api.dto.LoginRequest

interface LoginUserInputPort {
    fun execute(request: LoginRequest): AuthResponse
}
