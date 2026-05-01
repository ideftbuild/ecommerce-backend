package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductFilter
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.GetAllProductsInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.product.domain.model.Money
import com.ideftbuild.ecommerce_backend.product.domain.model.ProductQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Currency

@Service
class GetAllProductsUseCase (
    val productOutputPort: ProductOutputPort
): GetAllProductsInputPort {

    override fun execute(filter: ProductFilter, pageable: Pageable): Page<ProductResponse> {
        val products = productOutputPort.findAll(
            ProductQuery(
                filter.name,
                filter.minPrice,
                filter.maxPrice,
                filter.currency,
                filter.categoryId,
                filter.categorySlug
            ),
            pageable)

        return products.map { product ->
            ProductResponse.from(product)
        }
    }
}
