package com.ideftbuild.ecommerce_backend.product.api

import com.ideftbuild.ecommerce_backend.product.api.dto.CreateProductRequest
import com.ideftbuild.ecommerce_backend.product.api.dto.CreateVariantRequest
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductFilter
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponseAssembler
import com.ideftbuild.ecommerce_backend.product.api.dto.UpdateProductRequest
import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.ActivateProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.AddVariantInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.CreateProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.DeactivateProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.GetProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.DeleteProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.GetAllProductsInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.RemoveVariantInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.RestoreProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.RestoreVariantInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.input.UpdateProductInputPort
import com.ideftbuild.ecommerce_backend.product.application.usecase.GetAllProductsUseCase
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.ProductSpecification
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
//import org.springframework.data.web.PagedModel
import org.springframework.hateoas.PagedModel // remove this
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.hateoas.EntityModel
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PutMapping
import java.util.UUID

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val get: GetProductInputPort,
    private val create: CreateProductInputPort,
    private val getAll: GetAllProductsInputPort,
    private val delete: DeleteProductInputPort,
    private val update: UpdateProductInputPort,
    private val restore: RestoreProductInputPort,
    private val activate: ActivateProductInputPort,
    private val deactivate: DeactivateProductInputPort,
    private val responseAssembler: ProductResponseAssembler,
    private val pagedResourcesAssembler: PagedResourcesAssembler<ProductResponse>
) {

    @Operation(
        summary = "Retrieve a product by its ID",
        description = "Fetches a single product using its unique identifier."
    )
    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): ResponseEntity<EntityModel<ProductResponse>> {
        return ResponseEntity.ok(responseAssembler.toModel(get.execute(id)))
    }

    @Operation(
        summary = "Create a new product",
        description = "Creates a new product using the provided information."
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    fun create(@Valid @RequestBody request: CreateProductRequest): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(create.execute(request))
    }

    @Operation(
        summary = "Retrieve products",
        description = "Returns a paginated list of products based on the provided filter and pagination parameters."
    )
    @GetMapping("/")
    fun getAll(
        @ParameterObject filter: ProductFilter,
        @ParameterObject pageable: Pageable,
        ): ResponseEntity<PagedModel<EntityModel<ProductResponse>>> {
        val page = getAll.execute(filter, pageable)
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(page, responseAssembler))
    }

    @Operation(
        summary = "Delete a product",
        description = "Deletes the product identified by the given ID."
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Void> {
        delete.execute(id)
        return ResponseEntity.noContent().build()
    }

    @Operation(
        summary = "Update a product",
        description = "Update product fields "
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @Valid @RequestBody request: UpdateProductRequest)
    : ResponseEntity<EntityModel<ProductResponse>> {
        return ResponseEntity.ok(responseAssembler.toModel(update.execute(id, request)))
    }

    @Operation(
        summary = "Restore a product",
        description = "Restores a previously deleted product identified by the given ID."
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/restore")
    fun restore(@PathVariable id: UUID): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok(restore.execute(id))
    }


    @Operation(
        summary = "Activate a product",
        description = "Mark status of product as active"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/activate")
    fun activate(@PathVariable id: UUID): ResponseEntity<EntityModel<ProductResponse>> {
        return ResponseEntity.ok(responseAssembler.toModel(activate.execute(id)))
    }

    @Operation(
        summary = "Deactivate a product",
        description = "Mark status of product as inactivate"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/deactivate")
    fun deactivate(@PathVariable id: UUID): ResponseEntity<EntityModel<ProductResponse>> {
        return ResponseEntity.ok(responseAssembler.toModel(deactivate.execute(id)))
    }

}
