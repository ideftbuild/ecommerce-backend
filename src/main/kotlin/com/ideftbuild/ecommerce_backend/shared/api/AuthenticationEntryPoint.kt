package com.ideftbuild.ecommerce_backend.shared.api

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class AuthenticationEntryPoint : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = "application/json"

        val message = when (authException) {
            is BadCredentialsException ->
                authException.message ?: "Invalid authentication credentials"

            else ->
                "Authentication is required to access this resource."
        }

        response.writer.write(
            """
            {
                "status": 401,
                "error": "Unauthorized",
                "message": "$message",
                "path": "${request.requestURI}"
            }
            """.trimIndent()
        )
    }
}
