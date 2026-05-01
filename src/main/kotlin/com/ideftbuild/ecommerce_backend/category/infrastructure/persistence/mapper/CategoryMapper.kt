package com.ideftbuild.ecommerce_backend.category.infrastructure.persistence.mapper

import com.ideftbuild.ecommerce_backend.category.domain.Category
import com.ideftbuild.ecommerce_backend.category.infrastructure.persistence.entity.CategoryEntity


fun Category.toEntity(): CategoryEntity  = CategoryEntity(
    id = this.id,
    name = this.name,
    description = this.description,
    slug = this.slug,
    deletedAt = this.deletedAt
)


fun CategoryEntity.toDomain(): Category = Category(
    id = this.id,
    name = this.name,
    description = this.description,
    slug = this.slug,
    deletedAt = this.deletedAt
)
