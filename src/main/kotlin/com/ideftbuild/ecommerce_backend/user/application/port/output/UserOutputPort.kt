package com.ideftbuild.ecommerce_backend.user.application.port.output

import com.ideftbuild.ecommerce_backend.user.domain.User
import java.util.UUID

interface UserOutputPort {
    fun findById(id: UUID): User?

    fun findByIdIncludingDeleted(id: UUID): User?

    fun findByUsername(username: String): User?

    fun findByEmail(email: String): User?

    fun existsByUsernameOrEmail(username: String, email: String): Boolean

    fun findByUsernameOrEmail(identifier: String): User?

    fun save(user: User): User

    fun findAll(): List<User>
}
