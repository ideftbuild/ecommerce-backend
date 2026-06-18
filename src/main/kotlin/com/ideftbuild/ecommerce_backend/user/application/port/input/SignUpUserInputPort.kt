package com.ideftbuild.ecommerce_backend.user.application.port.input

import com.ideftbuild.ecommerce_backend.user.api.dto.SignUpRequest
import com.ideftbuild.ecommerce_backend.user.api.dto.UserResponse

interface SignUpUserInputPort {
    fun execute(request: SignUpRequest): UserResponse
}
