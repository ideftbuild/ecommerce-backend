package com.ideftbuild.ecommerce_backend.product.api

import com.ideftbuild.ecommerce_backend.product.api.dto.CreateVariantRequest
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponseAssembler
import com.ideftbuild.ecommerce_backend.product.application.port.input.AddVariantInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.GetProductVariantsInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.GetVariantInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.RemoveVariantInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.RestoreVariantInputPort
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/products")
class ProductVariantController(
//    private val getVariant: GetVariantInputPort,
    private val getProductVariants: GetProductVariantsInputPort,
    private val addVariant: AddVariantInputPort,
    private val removeVariant: RemoveVariantInputPort,
    private val restoreVariant: RestoreVariantInputPort,
    private val responseAssembler: VariantResponseAssembler,

    ){

//    @Operation(
//        summary = "Get a variant",
//        description = "Get product variant"
//    )
//    @GetMapping("/{productId}/variants/{variantId}")
//    fun get(@PathVariable productId: UUID, @PathVariable variantId: UUID): ResponseEntity<EntityModel<VariantResponse>> {
//        return ResponseEntity.ok(responseAssembler.toModel(getVariant.execute(variantId)))
//    }

    @Operation(
        summary = "Get product variants",
        description = "Get all variants in product"
    )
    @GetMapping("/{productId}/variants")
    fun getProductVariants(@PathVariable productId: UUID): ResponseEntity<List<EntityModel<VariantResponse>>> {
        return ResponseEntity.ok(
            getProductVariants.execute(productId).map { responseAssembler.toModel(it) }
        )
    }

    @Operation(
        summary = "Add a variant",
        description = "Add a new variant to product"
    )
    @PostMapping("/{productId}/variants")
    fun addVariant(@PathVariable productId: UUID, @RequestBody @Valid request: CreateVariantRequest): ResponseEntity<EntityModel<VariantResponse>> {
        return ResponseEntity.status(HttpStatus.CREATED).body(responseAssembler.toModel(addVariant.execute(productId , request)))
    }

    @Operation(
        summary = "Restore a variant",
        description = "Restores a previously deleted variant identified by the given ID."
    )
    @PatchMapping("/{productId}/variants/{variantId}/restore")
    fun restoreVariant(@PathVariable productId: UUID, @PathVariable variantId: UUID): ResponseEntity<EntityModel<VariantResponse>> {
        return ResponseEntity.ok(responseAssembler.toModel(restoreVariant.execute(productId, variantId)))
    }

    @Operation(
        summary = "Remove a variant",
        description = "Remove variant from product"
    )
    @DeleteMapping("/{productId}/variants/{variantId}")
    fun removeVariant(@PathVariable productId: UUID, @PathVariable variantId: UUID): ResponseEntity<Void> {
        removeVariant.execute(productId, variantId)
        return ResponseEntity.noContent().build()
    }
}
