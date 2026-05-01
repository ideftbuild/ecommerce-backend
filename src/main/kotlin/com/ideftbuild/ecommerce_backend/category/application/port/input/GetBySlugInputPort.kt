package com.ideftbuild.ecommerce_backend.category.application.port.input

import com.ideftbuild.ecommerce_backend.category.api.dto.CategoryResponse
import com.ideftbuild.ecommerce_backend.category.domain.Category

interface GetBySlugInputPort {
    fun execute(slug: String): CategoryResponse
}
