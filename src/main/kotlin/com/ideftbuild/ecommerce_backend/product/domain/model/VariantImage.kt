package com.ideftbuild.ecommerce_backend.product.domain.model

import java.time.Instant
import java.util.UUID

class VariantImage (
    var id: UUID? = null,

    var url: String,

    var originalName: String,

    var storageKey: String,

    var sortOrder: Int = 0,

    val variantId: UUID?,

//    var deletedAt: Instant? = null
)

fun MutableList<VariantImage>.removeFirstMatching(
    predicate: (VariantImage) -> Boolean
): VariantImage? {
    val it = iterator()

    while (it.hasNext()) {
        val item = it.next()
        if (predicate(item)) {
            it.remove()
            return item
        }
    }
    return null
}
