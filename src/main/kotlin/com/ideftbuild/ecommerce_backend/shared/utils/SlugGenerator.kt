package com.ideftbuild.ecommerce_backend.shared.utils
import java.text.Normalizer
import java.util.Locale

fun generateSlug(input: String): String {
    if (input.isBlank()) return ""

    val normalized = Normalizer.normalize(input, Normalizer.Form.NFD)

    val slug = normalized
        // remove diacritics (é → e, ñ → n)
        .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")

        .lowercase(Locale.getDefault())
        .trim()

        // replace common separators with space
        .replace("[&+/]".toRegex(), " and ")

        // remove apostrophes completely (men's → mens)
        .replace("'".toRegex(), "")

        // replace all non-alphanumeric with spaces
        .replace("[^a-z0-9\\s-]".toRegex(), " ")

        // collapse multiple spaces/hyphens
        .replace("\\s+".toRegex(), "-")
        .replace("-{2,}".toRegex(), "-")

        // remove leading/trailing hyphens
        .trim('-')

    return slug
}
