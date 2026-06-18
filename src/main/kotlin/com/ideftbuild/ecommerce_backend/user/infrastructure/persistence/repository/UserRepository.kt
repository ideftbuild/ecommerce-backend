package com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.repository

import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity.RoleEntity
import com.ideftbuild.ecommerce_backend.user.infrastructure.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface UserRepository: JpaRepository<UserEntity, UUID> {
    fun findByIdAndDeletedAtIsNull(id: UUID): UserEntity?

    fun findByUsernameAndDeletedAtIsNull(username: String): UserEntity?

    fun findByEmailAndDeletedAtIsNull(email: String): UserEntity?

    fun existsByUsernameOrEmailAndDeletedAtIsNull(username: String, email: String): Boolean

    @Query("""
        SELECT u FROM UserEntity u
        LEFT JOIN FETCH u.roles
        WHERE LOWER(u.username) = LOWER(:identifier)
        OR LOWER(u.email) = LOWER(:identifier)
        """)
    fun findByUsernameOrEmail(@Param("identifier") identifier: String): UserEntity?


    @Query("SELECT e FROM UserEntity e WHERE e.deletedAt IS NULL")
    fun findAllActive(): List<UserEntity>
//    fun findByUsernameOrEmail(username: String, email: String): UserEntity?
}
