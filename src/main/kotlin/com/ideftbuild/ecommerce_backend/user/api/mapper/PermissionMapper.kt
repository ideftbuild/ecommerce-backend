package com.ideftbuild.ecommerce_backend.user.api.mapper

import com.ideftbuild.ecommerce_backend.user.api.dto.PermissionResponse
import com.ideftbuild.ecommerce_backend.user.domain.Permission


fun Permission.toResponse(): PermissionResponse = PermissionResponse(
    id = this.id,
    name = this.name,
    description = this.description
)
