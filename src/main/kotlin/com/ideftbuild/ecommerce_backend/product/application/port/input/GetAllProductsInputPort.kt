package com.ideftbuild.ecommerce_backend.product.application.port.input

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductFilter
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GetAllProductsInputPort {
    fun execute(filter: ProductFilter, pageable: Pageable): Page<ProductResponse>
}
