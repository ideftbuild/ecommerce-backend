package com.ideftbuild.ecommerce_backend.user.application.port.output

import com.ideftbuild.ecommerce_backend.user.domain.Permission
import java.util.UUID

interface PermissionOutPort {

    fun findById(id: UUID): Permission?

    fun findAll(): List<Permission>
}
