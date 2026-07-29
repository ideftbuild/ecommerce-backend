package com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity

import com.ideftbuild.ecommerce_backend.shared.infrastructure.persistence.entity.AuditableEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Table
import jakarta.persistence.Id
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "permissions")
class PermissionEntity (
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(nullable = false, unique = true)
    val name: String,

    @Column
    val description:String
)
