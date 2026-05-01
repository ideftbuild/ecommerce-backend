package com.ideftbuild.ecommerce_backend.category.application.usecase

import com.ideftbuild.ecommerce_backend.category.api.dto.CategoryResponse
import com.ideftbuild.ecommerce_backend.category.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.category.application.port.input.GetAllCategoryInputPort
import com.ideftbuild.ecommerce_backend.category.application.port.output.CategoryOutputPort
import org.springframework.stereotype.Service

@Service
class GetAllCategoryUseCase (
    private val categoryOutputPort: CategoryOutputPort
): GetAllCategoryInputPort {
    override fun execute(): List<CategoryResponse> {
        val categories = categoryOutputPort.findAll()
        return categories.map { it.toResponse() }
    }

}
