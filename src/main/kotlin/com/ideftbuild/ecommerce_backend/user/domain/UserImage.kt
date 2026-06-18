package com.ideftbuild.ecommerce_backend.user.domain

import java.util.UUID

class UserImage (
    var id: UUID? = null,

    var url: String,

    var originalName: String,

    var storageKey: String,
//    var sortOrder: Int = 0,
)

fun MutableList<UserImage>.removeFirstMatching(
    predicate: (UserImage) -> Boolean
): UserImage? {
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
