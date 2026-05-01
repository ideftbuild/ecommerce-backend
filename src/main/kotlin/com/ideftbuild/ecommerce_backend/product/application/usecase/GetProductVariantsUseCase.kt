package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.GetProductVariantsInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.VariantOutputPort
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toResponse
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetProductVariantsUseCase(
    private val variantOutputPort: VariantOutputPort
): GetProductVariantsInputPort {
    override fun execute(productId: UUID): List<VariantResponse> {
        val variants = variantOutputPort.findByProductId(productId)
            ?: throw ResourceNotFoundException("product", productId)

        return variants.map { it.toResponse() }
    }
}
