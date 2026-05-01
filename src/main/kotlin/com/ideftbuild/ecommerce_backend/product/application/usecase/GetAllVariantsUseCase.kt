package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.VariantFilter
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.GetAllVariantsInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.VariantOutputPort
import com.ideftbuild.ecommerce_backend.product.domain.model.VariantQuery
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GetAllVariantsUseCase (
    private val variantOutputPort: VariantOutputPort
): GetAllVariantsInputPort {
    override fun execute(
        filter: VariantFilter,
        pageable: Pageable
    ): Page<VariantResponse> {

        println("sku: ${filter.sku}")
        println("quantity: ${filter.quantity}")
        val variants = variantOutputPort.findAll(
            VariantQuery(
                sku = filter.sku,
                quantity = filter.quantity
            ),
            pageable
        )

        return variants.map { variant ->
            VariantResponse.from(variant)
        }
    }
}
