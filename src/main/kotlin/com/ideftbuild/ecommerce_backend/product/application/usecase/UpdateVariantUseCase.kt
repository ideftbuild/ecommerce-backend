package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.UpdateVariantRequest
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.UpdateVariantInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.VariantOutputPort
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toResponse
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UpdateVariantUseCase (
    private val variantOutputPort: VariantOutputPort
): UpdateVariantInputPort {
    override fun execute(
        variantId: UUID,
        request: UpdateVariantRequest
    ): VariantResponse {
        val variant =  variantOutputPort.findById(variantId)
            ?: throw ResourceNotFoundException("variant", variantId)

        variant.update(request.price, request.quantity)

        return variantOutputPort.save(variant).toResponse()
    }
}
