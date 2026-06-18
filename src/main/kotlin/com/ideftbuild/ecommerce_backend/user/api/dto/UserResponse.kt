package com.ideftbuild.ecommerce_backend.user.api.dto

import java.util.UUID
import kotlin.uuid.Uuid

class UserResponse (
    val id: UUID?,

    val username: String,

    val firstName: String,

    val lastName: String,

    val email: String,

    val roles: List<RoleResponse>
)
