package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity

import com.ideftbuild.ecommerce_backend.shared.domain.common.AuditableEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.Instant
import java.util.UUID

@Entity
@Table(
    name = "variant_images",
    uniqueConstraints = [
        UniqueConstraint(
            columnNames = ["variant_id", "sort_order"]
        )
    ]
)
class VariantImageEntity(

    @Id
    @GeneratedValue
    var id: UUID? = null,

    @Column(nullable = false)
    var url: String,

    @Column(nullable = false)
    var originalName: String,

    @Column(nullable = false, unique = true)
    var storageKey: String,

    @Column(nullable = false)
    var sortOrder: Int = 0,

//    @Column(nullable = false)
//    var isPrimary: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", nullable = false)
    val variant: VariantEntity,

//    var deletedAt: Instant? = null

): AuditableEntity()
