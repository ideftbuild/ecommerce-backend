package com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity

import com.ideftbuild.ecommerce_backend.category.infrastructure.persistence.entity.CategoryEntity
import com.ideftbuild.ecommerce_backend.product.domain.model.ProductStatus
import com.ideftbuild.ecommerce_backend.shared.domain.common.AuditableEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OrderBy
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

@Entity
@Table(
    name = "products",
    indexes = [
        Index(name = "idx_product_name", columnList = "name", unique = true)
    ]
)
class ProductEntity (
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, length = 1000)
    var description: String,

    @Column(nullable = false, precision = 12, scale = 2)
    var minPrice: BigDecimal,

    @Column(nullable = false, precision = 12, scale = 2)
    var maxPrice: BigDecimal,


    @Column(nullable = false, length = 25)
    var currency: String,

    @OneToMany(
        mappedBy = "product",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    @Column(nullable = false)
    @OrderBy("price ASC")
    val variants: MutableList<VariantEntity> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    var category: CategoryEntity,

    @Column
    var deletedAt: Instant? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: ProductStatus = ProductStatus.ACTIVE,

    ): AuditableEntity()
