package com.ideftbuild.ecommerce_backend.product.domain.model

import java.math.BigDecimal
import java.util.Currency
import java.util.Locale
import java.util.UUID

data class ProductQuery(
    val name: String? = null,
    val minPrice: BigDecimal? = null,
    val maxPrice: BigDecimal? = null,
    var currency: String? = null,
    val categoryId: UUID? = null,
    val categorySlug: String? = null
) {
    init {
        if (!currency.isNullOrBlank()) {
            require(currency in SUPPORTED_CURRENCIES) { "Currency not supported" }
            currency = currency?.uppercase(Locale.ROOT)
        }
    }

    companion object {
        private val SUPPORTED_CURRENCIES = setOf("USD", "EUR", "NGN")
    }
}
