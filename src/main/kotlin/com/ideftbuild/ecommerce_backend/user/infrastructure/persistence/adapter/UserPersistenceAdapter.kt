package com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.adapter

import com.ideftbuild.ecommerce_backend.user.application.port.output.UserOutputPort
import com.ideftbuild.ecommerce_backend.user.domain.User
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.mapper.toDomain
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.mapper.toEntity
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserPersistenceAdapter (
private val jpaRepository: UserRepository
): UserOutputPort {
    override fun findById(id: UUID): User? {
        return jpaRepository.findByIdAndDeletedAtIsNull(id)?.toDomain()
    }

    override fun findByIdIncludingDeleted(id: UUID): User? {
        return jpaRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun findByUsername(username: String): User? {
        return jpaRepository.findByUsernameAndDeletedAtIsNull(username)?.toDomain()
    }

    override fun findByEmail(email: String): User? {
        return jpaRepository.findByEmailAndDeletedAtIsNull(email)?.toDomain()
    }

    override fun existsByUsernameOrEmail(username: String, email: String): Boolean {
       return jpaRepository.existsByUsernameOrEmailAndDeletedAtIsNull(username, email)
    }

    override fun findByUsernameOrEmail(identifier: String): User? {
        return jpaRepository.findByUsernameOrEmail(identifier)?.toDomain()
    }

    override fun save(user: User): User {
        return jpaRepository.save(user.toEntity()).toDomain()
    }

    override fun findAll(): List<User> {
        return jpaRepository.findAllActive().map { it.toDomain() }
    }
}
