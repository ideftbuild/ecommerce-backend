package com.ideftbuild.ecommerce_backend.product.api

import com.ideftbuild.ecommerce_backend.product.api.dto.ProductFilter
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.RepresentationModelAssembler
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Component
class ProductResponseAssembler: RepresentationModelAssembler<ProductResponse, EntityModel<ProductResponse>> {
    override fun toModel(response: ProductResponse): EntityModel<ProductResponse> {
        return EntityModel.of(
            response,
            linkTo(methodOn(ProductController::class.java)
                .get(requireNotNull(response?.id) { "Product id must not be null " })).withSelfRel(),
            linkTo(methodOn(ProductController::class.java)
                .getAll(ProductFilter(), Pageable.unpaged())).withRel("products")
        )
    }
}
