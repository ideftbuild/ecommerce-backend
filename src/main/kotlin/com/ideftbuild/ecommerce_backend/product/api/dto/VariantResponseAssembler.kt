package com.ideftbuild.ecommerce_backend.product.api.dto

import com.ideftbuild.ecommerce_backend.product.api.ProductVariantController
import com.ideftbuild.ecommerce_backend.product.api.VariantController
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.RepresentationModelAssembler
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Component
class VariantResponseAssembler: RepresentationModelAssembler<VariantResponse, EntityModel<VariantResponse>> {

    override fun toModel(response: VariantResponse): EntityModel<VariantResponse> {
        return EntityModel.of(
            response,
            linkTo(
                methodOn(VariantController::class.java)
                    .get(
                        requireNotNull(response.id) { "Variant id must not be null " })
            )
                .withSelfRel(),

            linkTo(
                methodOn(ProductVariantController::class.java)
                    .getProductVariants(response.productId!!)
            ).withRel("variants")
        )
    }
}
