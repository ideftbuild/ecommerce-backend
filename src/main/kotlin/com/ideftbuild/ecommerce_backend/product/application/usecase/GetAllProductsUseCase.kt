package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductFilter
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.GetAllProductsInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.product.domain.model.ProductQuery
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.ProductSpecification
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GetAllProductsUseCase (
    val productOutputPort: ProductOutputPort
): GetAllProductsInputPort {
    override fun execute(filter: ProductFilter, pageable: Pageable): Page<ProductResponse> {
        val products = productOutputPort.findAll(
            ProductQuery(filter.name, filter.minPrice, filter.maxPrice), pageable)

        return products.map { product ->
            ProductResponse.from(product)
        }
    }
}
