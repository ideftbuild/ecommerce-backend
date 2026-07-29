package com.ideftbuild.ecommerce_backend.shared.exception

import org.springframework.security.core.AuthenticationException

class JwtExpiredAuthenticationException(
    message: String,
    cause: Throwable
) : AuthenticationException(message, cause)
