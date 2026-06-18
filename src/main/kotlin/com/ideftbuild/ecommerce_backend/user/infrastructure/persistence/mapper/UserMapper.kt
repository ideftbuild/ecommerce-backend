package com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.mapper

import com.ideftbuild.ecommerce_backend.user.domain.User
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity.UserEntity


fun User.toEntity(): UserEntity = UserEntity(
    id = this.id,
    username = this.username,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    password = this.password,
    roles = this.roles.mapTo(mutableSetOf()) { it.toEntity() },
    deletedAt = this.deletedAt
)

fun UserEntity.toDomain(): User = User(
    id = this.id,
    username = this.username,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    password = this.password,
    roles = this.roles.mapTo(mutableSetOf()) { it.toDomain() },
    deletedAt = this.deletedAt
)

