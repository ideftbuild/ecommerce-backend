package com.ideftbuild.ecommerce_backend.user.application.port.input

import java.util.UUID

interface RestoreUserInputPort {
    fun execute(id: UUID)
}
