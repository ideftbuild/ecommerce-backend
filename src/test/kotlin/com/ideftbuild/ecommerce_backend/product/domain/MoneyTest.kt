package com.ideftbuild.ecommerce_backend.product.domain

import com.ideftbuild.ecommerce_backend.product.domain.model.Money
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.util.Currency
import kotlin.test.Test

class MoneyTest {
    @Test
    fun `should create Money successful`() {
        assertDoesNotThrow { Money.of(BigDecimal("100"), Currency.getInstance("USD")) }
    }

    @Test
    fun `should not have a negative amount`() {
        assertThrows<IllegalArgumentException> { Money.of(BigDecimal("-1"), Currency.getInstance("USD")) }
    }

    @Test
    fun `should support currency`() {
        assertThrows<IllegalArgumentException> { Money.of(BigDecimal("100"), Currency.getInstance("nig")) }
    }

}
