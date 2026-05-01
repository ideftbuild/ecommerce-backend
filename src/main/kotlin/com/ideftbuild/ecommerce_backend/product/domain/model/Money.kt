package com.ideftbuild.ecommerce_backend.product.domain.model

import java.math.BigDecimal
import java.util.Currency
import java.util.Locale

class Money private constructor (
    var amount: BigDecimal,
    var currency: Currency
): Comparable<Money> {

    init {
        require(amount >= BigDecimal.ZERO) { "Price must be positive" }
        require(currency in SUPPORTED_CURRENCIES) { "Currency not supported" }
    }

    companion object {
        private val SUPPORTED_CURRENCIES = setOf(
            Currency.getInstance("USD"),
            Currency.getInstance("EUR"),
            Currency.getInstance("NGN"),
        )

        fun of(amount: BigDecimal, currency: Currency): Money = Money(amount, currency)

        fun resolveCurrency(currency: String?): Currency? {
            if (currency.isNullOrBlank()) return null

            val currency = Currency.getInstance(currency.uppercase(Locale.ROOT))
            require(currency in SUPPORTED_CURRENCIES) { "Currency not supported" }
            return currency
        }
    }

    fun add(other: Money): Money {
        require(currency == other.currency) { "Currency mismatch" }
        return Money(amount + other.amount, currency)
    }

    override fun compareTo(other: Money): Int {
       return this.amount.compareTo(other.amount)
    }
}
