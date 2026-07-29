package com.ideftbuild.ecommerce_backend.user.api.mapper

import com.ideftbuild.ecommerce_backend.user.api.dto.RoleResponse
import com.ideftbuild.ecommerce_backend.user.domain.Role


fun Role.toResponse(): RoleResponse = RoleResponse(
    id = this.id,
    name = this.name,
    description = this.description,
    isSystem = this.isSystem,
    permissions = this.permissions.map { it.toResponse() }
)
