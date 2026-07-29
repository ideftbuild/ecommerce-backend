package com.ideftbuild.ecommerce_backend.user.domain

import java.time.Instant
import java.util.UUID

class Permission (
    val id: UUID? = null,

    val name: String,

    val description: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Permission) return false

        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
