package com.ideftbuild.ecommerce_backend.user.application.port.input

import com.ideftbuild.ecommerce_backend.user.api.dto.UpdateUserRequest
import com.ideftbuild.ecommerce_backend.user.api.dto.UserResponse
import java.util.UUID

interface UpdateUserInputPort {
    fun execute(id: UUID, request: UpdateUserRequest): UserResponse

    fun execute(username: String, request: UpdateUserRequest): UserResponse
}
