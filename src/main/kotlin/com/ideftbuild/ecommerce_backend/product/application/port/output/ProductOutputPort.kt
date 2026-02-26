package com.ideftbuild.ecommerce_backend.product.application.port.output

import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import com.ideftbuild.ecommerce_backend.product.domain.model.ProductQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface ProductOutputPort {
    fun save(product: Product): Product

    fun findById(id: UUID): Product?

    fun findByIdIncludingDeleted(id: UUID): Product?

    fun deleteById(id: UUID)

    fun findAll(query: ProductQuery, pageable: Pageable): Page<Product>
}
