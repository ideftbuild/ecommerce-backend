package com.ideftbuild.ecommerce_backend.shared.application.port.input

import com.ideftbuild.ecommerce_backend.user.domain.User

interface TokenProvider {
    fun generateToken(username: String, roles: List<String>): String
    fun extractUsername(token: String): String
    fun isValid(token: String): Boolean
}
