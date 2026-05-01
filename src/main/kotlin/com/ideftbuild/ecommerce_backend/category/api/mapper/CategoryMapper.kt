package com.ideftbuild.ecommerce_backend.category.api.mapper

import com.ideftbuild.ecommerce_backend.category.api.dto.CategoryResponse
import com.ideftbuild.ecommerce_backend.category.domain.Category


fun Category.toResponse(): CategoryResponse  = CategoryResponse(
    id = this.id!!,
    name = this.name,
    description = this.description,
    slug = this.slug
)
