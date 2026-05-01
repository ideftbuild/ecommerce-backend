package com.ideftbuild.ecommerce_backend.category.infrastructure.persistence.adapter

import com.ideftbuild.ecommerce_backend.category.application.port.output.CategoryOutputPort
import com.ideftbuild.ecommerce_backend.category.domain.Category
import com.ideftbuild.ecommerce_backend.category.infrastructure.persistence.mapper.toDomain
import com.ideftbuild.ecommerce_backend.category.infrastructure.persistence.mapper.toEntity
import com.ideftbuild.ecommerce_backend.category.infrastructure.persistence.repository.JpaCategoryRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CategoryPersistenceAdapter(
    private val jpaRepository: JpaCategoryRepository,
) : CategoryOutputPort {

    override fun save(category: Category): Category {
        val saved = jpaRepository.save(category.toEntity())
        return saved.toDomain()
    }

    override fun findById(id: UUID): Category? {
        return jpaRepository.findByIdAndDeletedAtIsNull(id)?.toDomain()
    }

    override fun findBySlug(slug: String): Category? {
        return jpaRepository.findBySlugAndDeletedAtIsNull(slug)?.toDomain()
    }

    override fun findAll(): List<Category> {
        return jpaRepository.findAllByDeletedAtIsNull()
            .map { it.toDomain() }
    }

    override fun existsBySlug(slug: String): Boolean {
        return jpaRepository.existsBySlugAndDeletedAtIsNull(slug)
    }
}
