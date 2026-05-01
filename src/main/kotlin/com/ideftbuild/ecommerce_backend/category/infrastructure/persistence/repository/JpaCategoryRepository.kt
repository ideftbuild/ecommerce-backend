package com.ideftbuild.ecommerce_backend.category.infrastructure.persistence.repository

import com.ideftbuild.ecommerce_backend.category.infrastructure.persistence.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface JpaCategoryRepository : JpaRepository<CategoryEntity, UUID> {
    fun findByIdAndDeletedAtIsNull(id: UUID): CategoryEntity?
    fun findBySlugAndDeletedAtIsNull(slug: String): CategoryEntity?
    fun findAllByDeletedAtIsNull(): List<CategoryEntity>
    fun existsBySlugAndDeletedAtIsNull(slug: String): Boolean
}
