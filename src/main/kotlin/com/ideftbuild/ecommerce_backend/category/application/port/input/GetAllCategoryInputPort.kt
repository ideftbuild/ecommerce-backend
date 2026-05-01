package com.ideftbuild.ecommerce_backend.category.application.port.input

import com.ideftbuild.ecommerce_backend.category.api.dto.CategoryResponse
import java.util.UUID

interface GetAllCategoryInputPort {
    fun execute(): List<CategoryResponse>
}
