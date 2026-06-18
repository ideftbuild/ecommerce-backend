package com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity

import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.VariantEntity


import com.ideftbuild.ecommerce_backend.shared.infrastructure.persistence.entity.AuditableEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(
    name = "user_image",
)
class UserImageEntity (
    @Id
    @GeneratedValue
    var id: UUID? = null,

    @Column(nullable = false)
    var url: String,

    @Column(nullable = false)
    var originalName: String,

    @Column(nullable = false, unique = true)
    var storageKey: String?,
//    @Column(nullable = false)
//    var isPrimary: Boolean = false,
//    var deletedAt: Instant? = null
): AuditableEntity()
