package com.ideftbuild.ecommerce_backend.product.api.dto

import com.ideftbuild.ecommerce_backend.product.api.ProductController
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.RepresentationModelAssembler
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component

@Component
class ProductResponseAssembler: RepresentationModelAssembler<ProductResponse, EntityModel<ProductResponse>> {
    override fun toModel(response: ProductResponse): EntityModel<ProductResponse> {
        return EntityModel.of(
            response,
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ProductController::class.java)
                .get(requireNotNull(response.id) { "Product id must not be null " })
            ).withSelfRel(),
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ProductController::class.java)
                    .getAll(ProductFilter(), Pageable.unpaged())
            ).withRel("products")
        )
    }
}
