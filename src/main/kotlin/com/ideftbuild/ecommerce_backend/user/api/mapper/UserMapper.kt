package com.ideftbuild.ecommerce_backend.user.api.mapper

import com.ideftbuild.ecommerce_backend.user.api.dto.UserResponse
import com.ideftbuild.ecommerce_backend.user.domain.User


fun User.toResponse(): UserResponse = UserResponse(
    id = this.id,
    username = this.username,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    roles = this.roles.map { it.toResponse() }
)
