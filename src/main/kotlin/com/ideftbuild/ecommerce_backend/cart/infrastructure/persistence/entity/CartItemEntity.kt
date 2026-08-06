package com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.entity

import com.ideftbuild.ecommerce_backend.cart.domain.CartItem
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.VariantEntity
import com.ideftbuild.ecommerce_backend.shared.infrastructure.persistence.entity.AuditableEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.util.UUID

@Entity
@Table(
    name = "cart_items",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_cart_item_cart_variant",
            columnNames = ["cart_id", "variant_id"]
        )
    ]
)
class CartItemEntity(

    @Id
    @GeneratedValue
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    var cart: CartEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", nullable = false)
    var variant: VariantEntity,

    @Column(nullable = false)
    var quantity: Int = 1

) : AuditableEntity() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CartItemEntity) return false

        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
