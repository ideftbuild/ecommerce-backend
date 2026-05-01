package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.RestoreVariantInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.VariantOutputPort
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toResponse
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RestoreVariantUseCase(
    private val productOutputPort: ProductOutputPort,
    private val variantOutputPort: VariantOutputPort
): RestoreVariantInputPort {
    override fun execute(
        productId: UUID,
        variantId: UUID
    ): VariantResponse {

        if (!productOutputPort.existsById(productId)) throw ResourceNotFoundException("product", productId)

        val variant = variantOutputPort.findByIdAndProductIdIncludingDeleted(variantId, productId)
            ?: throw ResourceNotFoundException("variant", variantId)

        variant.restore()

        return variantOutputPort.save(variant).toResponse()
    }
}
