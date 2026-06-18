package com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.mapper

import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity.RoleEntity;
import com.ideftbuild.ecommerce_backend.user.domain.Role

fun Role.toEntity(): RoleEntity = RoleEntity(
    id = this.id,
    name = this.name,
    description = this.description,
    deletedAt = this.deletedAt
)

fun RoleEntity.toDomain(): Role = Role(
    id = this.id,
    name = this.name,
    description = this.description,
    deletedAt = this.deletedAt
)
