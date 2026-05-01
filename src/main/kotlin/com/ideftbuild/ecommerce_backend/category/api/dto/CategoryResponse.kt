package com.ideftbuild.ecommerce_backend.category.api.dto

import java.util.UUID

class CategoryResponse(
    val id: UUID,
    val name: String,
    val description: String?,
    val slug: String,
)
