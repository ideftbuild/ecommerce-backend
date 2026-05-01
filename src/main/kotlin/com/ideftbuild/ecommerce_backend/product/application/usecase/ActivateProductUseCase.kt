package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.ActivateProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ActivateProductUseCase(
    private val productOutputPort: ProductOutputPort
): ActivateProductInputPort {
    override fun execute(id: UUID): ProductResponse {
        val product = productOutputPort.findById(id)
            ?: throw ResourceNotFoundException("product", id)

        product.activate()

        return ProductResponse.from(productOutputPort.save(product))
    }

}
