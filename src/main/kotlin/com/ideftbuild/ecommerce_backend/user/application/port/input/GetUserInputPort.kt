package com.ideftbuild.ecommerce_backend.user.application.port.input

import com.ideftbuild.ecommerce_backend.user.api.dto.UserResponse
import java.util.UUID

interface GetUserInputPort {
    fun execute(id: UUID): UserResponse

    fun execute(username: String): UserResponse
}
