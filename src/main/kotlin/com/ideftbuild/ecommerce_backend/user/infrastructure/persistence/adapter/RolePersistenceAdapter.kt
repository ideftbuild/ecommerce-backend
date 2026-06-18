package com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.adapter

import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import com.ideftbuild.ecommerce_backend.product.infrastructure.persistence.mapper.toDomain
import com.ideftbuild.ecommerce_backend.user.application.port.output.RoleOutputPort
import com.ideftbuild.ecommerce_backend.user.domain.Role
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.mapper.toDomain
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.mapper.toEntity
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.repository.RoleRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class RolePersistenceAdapter(
    private val jpaRepository: RoleRepository
): RoleOutputPort {
    override fun findByIdIncludingDeleted(id: UUID): Role? {
        val entity = jpaRepository.findById(id).orElse(null)
        return entity?.toDomain()
    }

    override fun findById(id: UUID): Role? {
        val entity = jpaRepository.findByIdAndDeletedAtIsNull(id) ?: return null
        return entity.toDomain()
    }

    override fun findByName(name: String): Role? {
        val entity = jpaRepository.findByNameAndDeletedAtIsNull(name) ?: return null
        return entity.toDomain()
    }

    override fun save(role: Role): Role {
        return jpaRepository.save(role.toEntity()).toDomain()
    }

    override fun findAll(): List<Role> {
        return jpaRepository.findAllActive().map { it.toDomain() }
    }
}
