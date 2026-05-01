package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.CreateVariantRequest
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.api.mapper.toVariant
import com.ideftbuild.ecommerce_backend.product.application.port.input.AddVariantInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.VariantOutputPort
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toResponse
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AddVariantUseCase (
    private val variantOutputPort: VariantOutputPort,
    private val productOutputPort: ProductOutputPort,
): AddVariantInputPort {
    override fun execute(productId: UUID, request: CreateVariantRequest): VariantResponse {
        val currency = productOutputPort.findCurrencyById(productId)
            ?: throw ResourceNotFoundException("product", productId)


        return variantOutputPort
            .save(
                request.toVariant(
                    productId = productId,
                    currency = currency))
            .toResponse()
    }

}
