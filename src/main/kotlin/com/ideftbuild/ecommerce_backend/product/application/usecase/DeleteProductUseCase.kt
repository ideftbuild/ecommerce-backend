package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.DeleteProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeleteProductUseCase (
   private val productOutputPort: ProductOutputPort
): DeleteProductInputPort {

    override fun execute(id: UUID) {
        val product = productOutputPort.findById(id)
            ?: throw ResourceNotFoundException("product", id)

        product.softDelete()

        ProductResponse.from(productOutputPort.save(product))
    }
}
