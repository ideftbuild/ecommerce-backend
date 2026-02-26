package com.ideftbuild.ecommerce_backend.shared.api

data class ErrorResponse(
    val message: String,
    val errors: Map<String, String>?
)
