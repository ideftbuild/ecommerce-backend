package com.ideftbuild.ecommerce_backend.user.application.port.output

import com.ideftbuild.ecommerce_backend.user.domain.Role
import java.util.UUID

interface RoleOutputPort {

    fun findByIdIncludingDeleted(id: UUID): Role?

    fun findById(id: UUID): Role?

    fun findByName(name: String): Role?

    fun save(role: Role): Role

    fun findAll(): List<Role>
}
