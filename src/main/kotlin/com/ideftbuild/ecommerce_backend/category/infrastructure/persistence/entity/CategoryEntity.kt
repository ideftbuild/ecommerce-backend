package com.ideftbuild.ecommerce_backend.category.infrastructure.persistence.entity

import com.ideftbuild.ecommerce_backend.shared.domain.common.AuditableEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(
    name = "categories",
    indexes = [
        Index(name = "idx_category_slug", columnList = "slug", unique = true)
    ]
)
class CategoryEntity(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(nullable = false)
    var name: String,

    @Column(length = 500)
    var description: String?,

    @Column(unique = true, nullable = false)
    var slug: String,

    @Column(name = "deleted_at")
    var deletedAt: Instant? = null
) : AuditableEntity()
