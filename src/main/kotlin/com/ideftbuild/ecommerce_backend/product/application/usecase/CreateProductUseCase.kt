package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.category.application.port.output.CategoryOutputPort
import com.ideftbuild.ecommerce_backend.product.api.dto.CreateProductRequest
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.api.mapper.toVariant
import com.ideftbuild.ecommerce_backend.product.application.port.input.CreateProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.ProductOutputPort
import com.ideftbuild.ecommerce_backend.product.domain.model.Money
import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import com.ideftbuild.ecommerce_backend.product.domain.model.Variant
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.shared.utils.generateSku
import jakarta.xml.bind.ValidationException
import org.springframework.stereotype.Service
import java.util.Currency

@Service
class CreateProductUseCase(
    private val productOutputPort: ProductOutputPort,

    private val categoryOutputPort: CategoryOutputPort,

): CreateProductInputPort {

    override fun execute(request: CreateProductRequest): ProductResponse {
        val category = categoryOutputPort.findById(request.categoryId)
            ?: throw ResourceNotFoundException("category", request.categoryId)

        if (category.isDeleted()) {
            throw ValidationException("Cannot create product with deleted category")
        }

//        request.sku = request.sku ?: SkuGenerator.generate(category.slug)

        val currency = Currency.getInstance(request.currency)

        var product = Product.create(
            name = request.name,
            description = request.description,
            currency = currency,
            category = category,
            variants = request.variants.map { variant ->
                variant.toVariant(currency = currency)
            } as MutableList<Variant>
        )

        product = productOutputPort.save(product)
        return ProductResponse.from(product)
    }
}
