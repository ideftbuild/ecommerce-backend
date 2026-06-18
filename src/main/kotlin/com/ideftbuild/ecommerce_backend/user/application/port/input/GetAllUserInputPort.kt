package com.ideftbuild.ecommerce_backend.user.application.port.input

import com.ideftbuild.ecommerce_backend.user.api.dto.UserResponse

interface GetAllUserInputPort {
    fun execute(): List<UserResponse>
}
