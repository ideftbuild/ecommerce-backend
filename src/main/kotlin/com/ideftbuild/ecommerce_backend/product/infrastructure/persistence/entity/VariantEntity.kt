package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity

import com.ideftbuild.ecommerce_backend.shared.infrastructure.persistence.entity.AuditableEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OrderBy
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.UUID
import java.math.BigDecimal
import java.time.Instant

@Entity
@Table(name = "variants")
class VariantEntity (

    @Id
    @GeneratedValue
    var id: UUID? = null,

    @Column(nullable = false, unique = true)
    val sku: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: ProductEntity,

    @Column(nullable = false, precision = 12, scale = 2)
    var price: BigDecimal,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    val attributes: Map<String, Any> = emptyMap(),

    @Column(nullable = false)
    var quantity: Long,

    @Column
    @OneToMany(
        mappedBy = "variant",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    @OrderBy("sortOrder ASC")
    var images: MutableList<VariantImageEntity> = mutableListOf(),

    @Column
    var deletedAt: Instant? = null,

    @Column(nullable = false, columnDefinition = "boolean default false")
    var deletedByParent: Boolean = false

): AuditableEntity()
// TODO: Consider adding currency field
