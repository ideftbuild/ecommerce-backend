package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.GetProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

@Service
class GetProductUseCase(
    private val productOutputPort: ProductOutputPort
): GetProductInputPort  {


    override fun execute(id: UUID): ProductResponse {
        val product = productOutputPort.findById(id)
            ?: throw ResourceNotFoundException("product", id)

        return ProductResponse.from(product)
    }
}
