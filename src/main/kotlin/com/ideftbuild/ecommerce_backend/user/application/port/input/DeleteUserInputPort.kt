package com.ideftbuild.ecommerce_backend.user.application.port.input

import java.util.UUID

interface DeleteUserInputPort {
    fun execute(id: UUID)

    fun execute(username: String)
}
