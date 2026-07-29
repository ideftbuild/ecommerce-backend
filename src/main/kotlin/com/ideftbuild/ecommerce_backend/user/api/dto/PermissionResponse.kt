package com.ideftbuild.ecommerce_backend.user.api.dto

import java.util.UUID

class PermissionResponse(
    val id: UUID?,
    val name: String,
    val description: String
)
