package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.DeactivateProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeactivateProductUseCase(
    private val productOutputPort: ProductOutputPort
): DeactivateProductInputPort {
    override fun execute(id: UUID): ProductResponse {
       var product = productOutputPort.findById(id)
           ?: throw ResourceNotFoundException("product", id)

        println("product before deactivation status: ${product.status.name}")
        product.deactivate()

        product = productOutputPort.save(product)
        println("product after deactivation status: ${product.status.name}")
        return  ProductResponse.from(product)
    }

}
