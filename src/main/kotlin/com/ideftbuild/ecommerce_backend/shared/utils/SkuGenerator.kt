package com.ideftbuild.ecommerce_backend.shared.utils

import java.util.UUID

fun generateSku(prefix: String): String {

    println("prefix is: $prefix")
    val randomPart = UUID.randomUUID()
        .toString()
        .replace("-", "")
        .take(8)
        .uppercase()

    return if (prefix.isBlank()) {
        randomPart
    } else {
        "${prefix.uppercase()}-$randomPart"
    }
}
