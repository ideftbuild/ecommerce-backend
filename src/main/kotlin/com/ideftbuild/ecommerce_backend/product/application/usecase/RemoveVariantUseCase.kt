package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.RemoveVariantInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.VariantOutputPort
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toResponse
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RemoveVariantUseCase(
    private val variantOutputPort: VariantOutputPort,
): RemoveVariantInputPort {
    override fun execute(productId: UUID, variantId: UUID) {
        val variant = variantOutputPort.findByIdAndProductId(variantId, productId)
            ?: throw ResourceNotFoundException("variant", variantId)

        variant.softDelete()

        variantOutputPort.save(variant)
    }

}
