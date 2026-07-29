package com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity

import com.ideftbuild.ecommerce_backend.shared.infrastructure.persistence.entity.AuditableEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "roles")
class RoleEntity (
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(nullable = false, unique = true)
    val name: String,

    @Column
    val description: String?,

    @Column
    val isSystem: Boolean = false,

    @Column
    var deletedAt: Instant? = null,

    @ManyToMany
    @JoinTable(
        name = "role_permissions",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    val permissions: MutableSet<PermissionEntity> = mutableSetOf(),

    ): AuditableEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RoleEntity) return false

        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
