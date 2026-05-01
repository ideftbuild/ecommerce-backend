package com.ideftbuild.ecommerce_backend.category.domain

import java.time.Instant
import java.util.UUID

class Category (
    val id: UUID? = null,
    var name: String,
    var description: String?,
    val slug: String,
    var deletedAt: Instant? = null
) {
    companion object {
        fun create(name: String, description: String?, slug: String): Category {
            require(name.isNotBlank()) { "Category name is required" }
            require(slug.isNotBlank()) { "Category slug is required" }
//            require(slug.matches(Regex("^[a-z0-9-]+$"))) {
//                "Slug must contain only lowercase letters, numbers, and hyphens"
//            }

            return Category(
                name = name,
                description = description,
                slug = slug
            )
        }
    }

    fun updateName(newName: String): Category {
        require(newName.isNotBlank()) { "Category name is required" }
        this.name = newName
        return this
    }

    fun isDeleted(): Boolean = deletedAt != null

    fun softDelete(): Category {
        require(!isDeleted()) { "Category already deleted" }
        this.deletedAt = Instant.now()
        return this
    }
}
