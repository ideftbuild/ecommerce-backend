package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.GetVariantInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.VariantOutputPort
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toResponse
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetVariantUseCase(
    private val variantOutputPort: VariantOutputPort
): GetVariantInputPort {
    override fun execute(id: UUID): VariantResponse {
        val variant = variantOutputPort.findById(id)
            ?: throw ResourceNotFoundException("variant", id)

        return variant.toResponse()
    }
}
