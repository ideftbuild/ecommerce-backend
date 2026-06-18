package com.ideftbuild.ecommerce_backend.user.domain

import com.ideftbuild.ecommerce_backend.product.domain.model.Variant
import com.ideftbuild.ecommerce_backend.product.domain.model.VariantImage
import java.time.Instant
import java.util.UUID

const val MAX_NAME_LENGTH: Int = 32

const val MIN_NAME_LENGTH: Int = 3

class User (
    val id: UUID? = null,

    var username: String,

    var firstName: String,

    var lastName: String,

    val email: String,

    val password: String,

    var profileImage: UserImage? = null,

    val roles: MutableSet<Role> = mutableSetOf(),

    var deletedAt: Instant? = null
) {


    init {
        require(username.length in MIN_NAME_LENGTH..<MAX_NAME_LENGTH)
        require(firstName.length in MIN_NAME_LENGTH..<MAX_NAME_LENGTH)
        require(lastName.length in MIN_NAME_LENGTH..<MAX_NAME_LENGTH)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    companion object {
        fun create(
            username: String,
            firstName: String,
            lastName: String,
            email: String,
            password: String
        ): User {
            return User(
                username = username,
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password
            )
        }
    }

    fun softDelete() {
        require(this.deletedAt == null) { "User already deleted" }
        this.deletedAt = Instant.now()
    }

    fun restore() {
        require(this.deletedAt != null) { "User not deleted" }
        this.deletedAt = null
    }

    fun uploadImage(key: String, url: String, originalFilename: String?): User {
        this.profileImage = UserImage(
            url = url,
            originalName = originalFilename ?: "file",
            storageKey = key,
        )
        return this
    }

    fun update(
        username: String?,
        lastName: String?,
        firstName: String?) {

        username?.let { name ->
            require(name.length in MIN_NAME_LENGTH..<MAX_NAME_LENGTH)
            this.username = name
        }

        firstName?.let { name ->
            require(name.length in MIN_NAME_LENGTH..<MAX_NAME_LENGTH)
            this.firstName = name
        }

        lastName?.let { name ->
            require(name.length in MIN_NAME_LENGTH..<MAX_NAME_LENGTH)
            this.lastName = name
        }
    }
}
