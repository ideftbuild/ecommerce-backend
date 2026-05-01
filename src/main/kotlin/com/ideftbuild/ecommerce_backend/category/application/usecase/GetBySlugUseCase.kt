package com.ideftbuild.ecommerce_backend.category.application.usecase

import com.ideftbuild.ecommerce_backend.category.api.dto.CategoryResponse
import com.ideftbuild.ecommerce_backend.category.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.category.application.port.input.GetBySlugInputPort
import com.ideftbuild.ecommerce_backend.category.application.port.output.CategoryOutputPort
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class GetBySlugUseCase (
    private val categoryOutputPort: CategoryOutputPort
): GetBySlugInputPort {
    override fun execute(slug: String): CategoryResponse {
        val category = categoryOutputPort.findBySlug(slug)
            ?: throw ResourceNotFoundException("category", message = slug)

        return category.toResponse()
    }
}
