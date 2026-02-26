package com.ideftbuild.ecommerce_backend.product.domain.model

import java.math.BigDecimal
import java.util.Currency

class Money private constructor (
    var amount: BigDecimal,
    var currency: Currency
) {

    init {
        require(amount > BigDecimal.ZERO) { "Price must be positive" }
        require(currency in SUPPORTED_CURRENCIES) { "Currency not supported" }
    }

    companion object {
        private val SUPPORTED_CURRENCIES = setOf(
            Currency.getInstance("USD"),
            Currency.getInstance("EUR"),
            Currency.getInstance("NGN"),
        )

        fun of(amount: BigDecimal, currency: Currency): Money = Money(amount, currency)
    }

    fun add(other: Money): Money {
        require(currency == other.currency) { "Currency mismatch" }
        return Money(amount + other.amount, currency)
    }
}
