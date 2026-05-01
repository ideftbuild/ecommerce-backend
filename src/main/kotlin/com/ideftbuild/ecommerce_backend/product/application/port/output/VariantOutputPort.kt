package com.ideftbuild.ecommerce_backend.product.application.port.output

import com.ideftbuild.ecommerce_backend.product.api.dto.VariantResponse
import com.ideftbuild.ecommerce_backend.product.domain.model.Variant
import com.ideftbuild.ecommerce_backend.product.domain.model.VariantQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface VariantOutputPort {

    fun save(variant: Variant): Variant

    fun findById(id: UUID): Variant?

    fun findBySku(sku: String): Variant?

    fun findByIdAndProductId(variantId: UUID, productId: UUID): Variant?

    fun findByIdAndProductIdIncludingDeleted(variantId: UUID, productId: UUID): Variant?

    fun findByProductId(productId: UUID): List<Variant>?

    fun findAll(query: VariantQuery, pageable: Pageable): Page<Variant>
}
