package com.ideftbuild.ecommerce_backend.shared.exception

import java.util.UUID

open class ResourceNotFoundException (
    name: String,
    id: UUID? = null,
    message: String? = null,
): BusinessException("$name with id ${id ?: message} not found")

