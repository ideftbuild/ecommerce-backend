package com.ideftbuild.ecommerce_backend.category.application.usecase

import com.ideftbuild.ecommerce_backend.category.api.dto.CategoryResponse
import com.ideftbuild.ecommerce_backend.category.api.dto.CreateCategoryRequest
import com.ideftbuild.ecommerce_backend.category.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.category.application.port.input.CreateCategoryInputPort
import com.ideftbuild.ecommerce_backend.category.application.port.output.CategoryOutputPort
import com.ideftbuild.ecommerce_backend.category.domain.Category
import com.ideftbuild.ecommerce_backend.shared.utils.generateSlug
import org.springframework.stereotype.Service

@Service
class CreateCategoryUseCase (
    private val categoryOutputPort: CategoryOutputPort
): CreateCategoryInputPort {
    override fun execute(request: CreateCategoryRequest): CategoryResponse {

        val slug = generateSlug(request.name)

        println("Generated slug is: $slug")

        val category = Category.create(
            name = request.name,
            description = request.description,
            slug = slug
        )

        return categoryOutputPort.save(category).toResponse()
    }


}
