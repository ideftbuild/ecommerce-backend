package com.ideftbuild.ecommerce_backend.user.api.dto

import jakarta.validation.constraints.Size
import java.util.UUID


const val MAX_NAME_LENGTH: Int = 32

const val MIN_NAME_LENGTH: Int = 3

class UpdateUserRequest(
    @field:Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = "Lastname must be between $MIN_NAME_LENGTH to $MAX_NAME_LENGTH characters")
    val username: String?,

    @field:Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = "Lastname must be between $MIN_NAME_LENGTH to $MAX_NAME_LENGTH characters")
    val firstName: String?,

    @field:Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = "Lastname must be between $MIN_NAME_LENGTH to $MAX_NAME_LENGTH characters")
    val lastName: String?
)
