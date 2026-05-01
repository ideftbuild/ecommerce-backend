package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.api.dto.UpdateProductRequest
import com.ideftbuild.ecommerce_backend.product.application.port.input.UpdateProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.product.domain.model.Money
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.Currency
import java.util.UUID

@Service
class UpdateProductUseCase (
    private val productOutputPort: ProductOutputPort
): UpdateProductInputPort {
    override fun execute(id: UUID, request: UpdateProductRequest): ProductResponse {
        var product = productOutputPort.findById(id)
            ?: throw ResourceNotFoundException("product", id)

        product.update(
            name = request.name,
            description = request.description,
            currency = request.currency
        )

        product = productOutputPort.save(product)
        return ProductResponse.from(product)
    }
}
