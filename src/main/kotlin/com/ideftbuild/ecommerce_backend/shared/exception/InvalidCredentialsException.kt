package com.ideftbuild.ecommerce_backend.shared.exception

open class InvalidCredentialsException (
    message: String? = null
): BusinessException(message ?: "Invalid credentials")
