package com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.mapper

import com.ideftbuild.ecommerce_backend.user.domain.Permission
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity.PermissionEntity


fun Permission.toEntity(): PermissionEntity = PermissionEntity(
    id = this.id,
    name = this.name,
    description = this.description
)

fun PermissionEntity.toDomain(): Permission = Permission(
    id = this.id,
    name = this.name,
    description = this.description
)
