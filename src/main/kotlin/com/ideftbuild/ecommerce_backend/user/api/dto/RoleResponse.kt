package com.ideftbuild.ecommerce_backend.user.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

class RoleResponse (
    val id: UUID?,

    val name: String,

    val description: String?,

    @field:JsonProperty("isSystem")
    val isSystem: Boolean,

    val permissions: List<PermissionResponse>
)
