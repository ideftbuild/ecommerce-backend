package com.ideftbuild.ecommerce_backend.shared.exception

open class BusinessException (
    message: String,
    cause: Throwable? = null
): RuntimeException(message, cause)
