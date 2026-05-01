package com.ideftbuild.ecommerce_backend.product.api

import com.ideftbuild.ecommerce_backend.product.api.dto.UpdateVariantRequest
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantFilter
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantImageResponse
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponseAssembler
import com.ideftbuild.ecommerce_backend.product.application.port.input.DeleteImageInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.GetAllVariantsInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.GetVariantInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.UpdateVariantInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.UploadImageInputPort
import io.swagger.v3.oas.annotations.Operation
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@RestController
@RequestMapping("/api/v1/variants")
class VariantController(
    private val getAll: GetAllVariantsInputPort,

    private val get: GetVariantInputPort,

    private val update: UpdateVariantInputPort,

    private val uploadImage: UploadImageInputPort,

    private val deleteImage: DeleteImageInputPort,

    private val responseAssembler: VariantResponseAssembler,

    private val pagedResourcesAssembler: PagedResourcesAssembler<VariantResponse>
) {

    @Operation(
        summary = "Get a variant",
        description = "Get product variant"
    )
    @GetMapping("/{variantId}")
    fun get(@PathVariable variantId: UUID): ResponseEntity<EntityModel<VariantResponse>> {
        return ResponseEntity.ok(responseAssembler.toModel(get.execute(variantId)))
    }

    @Operation(
        summary = "Get variants",
        description = "Get paginated variants. Filter by sku, date, quantity"
    )
    @GetMapping("")
    fun getAll(
        @ParameterObject filter: VariantFilter,
        @ParameterObject pageable: Pageable
        ): ResponseEntity<PagedModel<EntityModel<VariantResponse>>> {

        val page = getAll.execute(filter, pageable)
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(page, responseAssembler))
    }

    @Operation(
        summary = "Update a variant",
        description = "Update variant price and quantity"
    )
    @PutMapping("/{variantId}")
    fun update(@PathVariable variantId: UUID, @RequestBody request: UpdateVariantRequest): ResponseEntity<VariantResponse> {
        return ResponseEntity.ok(update.execute(variantId, request))
    }


    @Operation(
        summary = "Upload images",
        description ="Upload variant images"
    )
    @PostMapping("/{variantId}/images", consumes = ["multipart/form-data"])
    fun uploadImage(@PathVariable variantId: UUID, @RequestParam files: List<MultipartFile>): ResponseEntity<List<VariantImageResponse>> {
        return ResponseEntity.ok(uploadImage.execute(variantId, files))
    }

    @Operation(
        summary = "Delete image",
        description ="Delete an uploaded variant image"
    )
    @DeleteMapping("/{variantId}/images")
    fun deleteImage(@PathVariable variantId: UUID, @RequestBody name: String): ResponseEntity<Void> {
        deleteImage.execute(variantId, name)
        return ResponseEntity.noContent().build()
    }
}
