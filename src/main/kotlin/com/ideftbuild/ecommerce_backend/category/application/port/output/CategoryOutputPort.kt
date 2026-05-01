package com.ideftbuild.ecommerce_backend.category.application.port.output

import com.ideftbuild.ecommerce_backend.category.domain.Category
import java.util.UUID

interface CategoryOutputPort {
    fun save(category: Category): Category
    fun findById(id: UUID): Category?
    fun findBySlug(slug: String): Category?
    fun findAll(): List<Category>
    fun existsBySlug(slug: String): Boolean
}
