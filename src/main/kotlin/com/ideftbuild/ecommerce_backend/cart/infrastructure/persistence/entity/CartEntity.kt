package com.ideftbuild.ecommerce_backend.cart.infrastructure.persistence.entity

import com.ideftbuild.ecommerce_backend.shared.infrastructure.persistence.entity.AuditableEntity
import com.ideftbuild.ecommerce_backend.user.domain.User
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity.UserEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "carts")
class CartEntity(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    var user: UserEntity,

    @OneToMany(
        mappedBy = "cart",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val items: MutableSet<CartItemEntity> = mutableSetOf(),

) : AuditableEntity()
