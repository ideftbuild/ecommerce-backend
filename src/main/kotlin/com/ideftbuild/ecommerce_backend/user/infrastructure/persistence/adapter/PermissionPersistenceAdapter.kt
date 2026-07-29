package com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.adapter

import com.ideftbuild.ecommerce_backend.user.application.port.output.PermissionOutPort
import com.ideftbuild.ecommerce_backend.user.domain.Permission
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.mapper.toDomain
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.repository.PermissionRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PermissionPersistenceAdapter (
    private val jpaRepository: PermissionRepository
): PermissionOutPort {

    override fun findById(id: UUID): Permission? {
        return jpaRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun findAll(): List<Permission> {
        return jpaRepository.findAll().map { it.toDomain() }
    }
}
