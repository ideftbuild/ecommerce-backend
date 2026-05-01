package com.ideftbuild.ecommerce_backend.product.application.port.input

import com.ideftbuild.ecommerce_backend.product.api.dto.VariantFilter
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GetAllVariantsInputPort {
    fun execute(filter: VariantFilter, pageable: Pageable): Page<VariantResponse>
}
