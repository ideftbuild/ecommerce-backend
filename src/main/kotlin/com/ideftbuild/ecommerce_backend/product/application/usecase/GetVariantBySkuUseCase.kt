package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.GetVariantBySkuInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.VariantOutputPort
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toResponse
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class GetVariantBySkuUseCase(
    private val variantOutputPort: VariantOutputPort
): GetVariantBySkuInputPort {

    override fun execute(sku: String): VariantResponse {
        val variant = variantOutputPort.findBySku(sku)
            ?: throw ResourceNotFoundException(name = "variant", message = sku)

        return variant.toResponse()
    }
}
