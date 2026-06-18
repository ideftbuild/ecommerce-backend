package com.ideftbuild.ecommerce_backend.user.application.port.input

import java.util.UUID

interface DeleteRoleInputPort {
    fun execute(id: UUID)

    fun execute(name: String)
}
