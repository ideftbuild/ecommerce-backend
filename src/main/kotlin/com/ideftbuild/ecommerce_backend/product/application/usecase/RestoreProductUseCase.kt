package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.RestoreProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RestoreProductUseCase (
    private val productOutputPort: ProductOutputPort
): RestoreProductInputPort {
    override fun execute(id: UUID): ProductResponse {
        val product = productOutputPort.findByIdIncludingDeleted(id)
            ?: throw ResourceNotFoundException("product", id)

        product.restore()

        return ProductResponse.from(productOutputPort.save(product))
    }

}
