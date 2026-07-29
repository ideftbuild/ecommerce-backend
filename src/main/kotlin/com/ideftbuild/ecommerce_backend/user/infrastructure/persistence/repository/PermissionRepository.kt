package com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.repository

import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity.PermissionEntity
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PermissionRepository: JpaRepository<PermissionEntity, UUID> {
}
