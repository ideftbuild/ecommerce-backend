package com.ideftbuild.ecommerce_backend.user.domain

import com.ideftbuild.ecommerce_backend.user.api.dto.RoleResponse
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity.UserEntity
import java.time.Instant
import java.util.UUID

class Role (
    val id: UUID? = null,

    val name: String,

    var description: String?,

    var isSystem: Boolean = false,

    val permissions: MutableSet<Permission> = mutableSetOf(),
//    val users: MutableSet<User> = mutableSetOf()
    var deletedAt: Instant? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Role) return false

        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    fun update(description: String): Role {
        this.description = description
        return this
    }

    fun softDelete() {
        require(deletedAt == null) { "Role already deleted" }
        this.deletedAt = Instant.now()
    }

    fun restore() {
        require(deletedAt != null) { "Role not deleted" }
        this.deletedAt = null
    }

    companion object {
        fun create(name: String, description: String?): Role {
            return Role(
                name = name,
                description = description
            )
        }
    }
}
