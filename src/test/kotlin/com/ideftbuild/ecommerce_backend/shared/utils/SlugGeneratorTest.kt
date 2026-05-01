package com.ideftbuild.ecommerce_backend.shared.utils

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class utils {
}

class SlugGenerator {

    @Test
    fun `test that it checks for blanks`() {
        assertEquals(generateSlug(""), "")
    }


    @Test
    fun `test that it removes accents`() {
        assertEquals(generateSlug("Café"), "cafe")
    }

    @Test
    fun `test that it normalizes to lower case`() {
        assertEquals(generateSlug("UP"), "up")
    }

    @Test
    fun `test that it removes redundant spaces`() {
        assertEquals(generateSlug("  space"), "space")
    }

    @Test
    fun `test that it replaces common separators with space`() {
        assertEquals(generateSlug("Shoe&"), "shoe-and")
        assertEquals(generateSlug("Bag+"), "bag-and")
        assertEquals(generateSlug("Test/"), "test-and")
    }

    @Test
    fun `test that it removes apostrophes completely`() {
        assertEquals(generateSlug("men's"), "mens")
    }

    @Test
    fun `test that it replaces all non-alphanumeric with spaces`() {
        assertEquals(generateSlug("mens;shoe"), "mens-shoe")
    }

    @Test
    fun `that that it replaces multiple spaces or hyphens`() {
        assertEquals(generateSlug("mens    shoe"), "mens-shoe")
        assertEquals(generateSlug("mens----shoe"), "mens-shoe")
    }

    @Test
    fun `test that it removes leading or trailing hyphens`() {
        assertEquals(generateSlug("shoe---"), "shoe")
        assertEquals(generateSlug("---shoe"), "shoe")
        assertEquals(generateSlug("---shoe---"), "shoe")
    }

    @Test
    fun `test with complex strings`() {
        assertEquals(
            generateSlug(
                "🚀 New! Men's Café-style Shirts & Niño's “Limited Edition” (50% OFF) — Summer 2025 / Special+Offer @ Lagos!!!  "
            ),
            "new-mens-cafe-style-shirts-and-ninos-limited-edition-50-off-summer-2025-and-special-and-offer-lagos"
        )
    }
}
