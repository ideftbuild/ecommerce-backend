package com.ideftbuild.ecommerce_backend.category.api

import com.ideftbuild.ecommerce_backend.category.api.dto.CategoryResponse
import com.ideftbuild.ecommerce_backend.category.api.dto.CreateCategoryRequest
import com.ideftbuild.ecommerce_backend.category.application.port.input.CreateCategoryInputPort
import com.ideftbuild.ecommerce_backend.category.application.port.input.GetAllCategoryInputPort
import com.ideftbuild.ecommerce_backend.category.application.port.input.GetBySlugInputPort
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductFilter
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponse
import com.ideftbuild.ecommerce_backend.product.api.dto.ProductResponseAssembler
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/categories")
class CategoryController(
    private val create: CreateCategoryInputPort,
    private val getAll: GetAllCategoryInputPort,
    private val getBySlug: GetBySlugInputPort,
) {
    @Operation(
        summary = "Get Categories",
        description = "Get all categories"
    )
    @GetMapping
    fun getAllCategories(): ResponseEntity<List<CategoryResponse>> {
         return ResponseEntity.ok(getAll.execute())
    }

    @Operation(
        summary = "Get category by slug",
        description = "Get category by slug"
    )
    @GetMapping("/{slug}")
    fun getBySlug(
        @ParameterObject slug: String,
    ): ResponseEntity<CategoryResponse> {
        return ResponseEntity.ok(getBySlug.execute(slug))
    }

    @Operation(
        summary = "Create Category",
        description = "Create a category"
    )
    @PostMapping
    fun createCategory(
        @Valid @RequestBody request: CreateCategoryRequest
    ): ResponseEntity<CategoryResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(create.execute(request))
    }
}
