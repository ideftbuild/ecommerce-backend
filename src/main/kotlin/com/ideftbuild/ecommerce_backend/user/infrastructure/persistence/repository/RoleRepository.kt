package com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.repository

import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.entity.ProductEntity
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RoleRepository: JpaRepository<RoleEntity, UUID> {
    fun findByIdAndDeletedAtIsNull(id: UUID): RoleEntity?
    fun findByNameAndDeletedAtIsNull(name: String): RoleEntity?

    @Query("SELECT e FROM RoleEntity e WHERE e.deletedAt IS NULL")
    fun findAllActive(): List<RoleEntity>
}
