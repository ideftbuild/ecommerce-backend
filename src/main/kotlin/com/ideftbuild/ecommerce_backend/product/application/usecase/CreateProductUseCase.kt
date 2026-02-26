package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.CreateProductRequest
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.CreateProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.product.domain.model.Money
import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import org.springframework.stereotype.Service
import java.util.Currency

@Service
class CreateProductUseCase(
    private val productOutputPort: ProductOutputPort
): CreateProductInputPort {

    override fun execute(request: CreateProductRequest): ProductResponse {
        // Implementation here

        println("control didn't return after dto checks it proceeded")
        var product = Product.create(
            id = null,
            name = request.name,
            description = request.description,
            price = Money.of(request.price, Currency.getInstance(request.currency)),
            quantity = request.quantity
        )

        product = productOutputPort.save(product)
        return ProductResponse.from(product)
    }
}
