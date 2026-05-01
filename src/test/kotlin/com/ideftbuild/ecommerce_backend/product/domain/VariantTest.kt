package com.ideftbuild.ecommerce_backend.product.domain

import com.ideftbuild.ecommerce_backend.product.domain.model.Money
import com.ideftbuild.ecommerce_backend.product.domain.model.Variant
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.time.Instant
import java.util.Currency
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VariantTest {

    lateinit var variant: Variant;

    @BeforeTest
    fun setUp() {
        variant = Variant(
            sku = "PROD-TEST-222",
            price = Money.of(BigDecimal("200"), Currency.getInstance("USD")),
            quantity = 2,
        )
    }

    @Test
    fun `should delete variant`() {
        variant.softDelete()
        assertTrue(variant.isDeleted())
        assertFalse(variant.deletedByParent)
    }

    @Test
    fun `should update product price`() {
        val newAmount = BigDecimal("100.01")
        variant.update(price = newAmount)
        assertEquals(variant.price.amount, newAmount)
    }

    @Test
    fun `should update product quantity`() {
        val newQuantity: Long = 10
        variant.update(quantity = newQuantity)
        assertEquals(variant.quantity, newQuantity)
    }

    @Test
    fun `should throw exception when quantity is negative`() {
        assertThrows<IllegalArgumentException> {
            variant.update(quantity = -10)
        }
    }

    @Test
    fun `should throw exception when price negative`() {
        assertThrows<IllegalArgumentException>{
            variant.update(price = BigDecimal("-1"))
        }
    }

    @Test
    fun `should update product price and quantity`() {
        val newAmount = BigDecimal("200.01")
        val newQuantity: Long = 20

        variant.update(price = newAmount, quantity = newQuantity)

        assertEquals(variant.price.amount, newAmount)
        assertEquals(variant.quantity, newQuantity)
    }

    @Test
    fun `should delete variant by parent`() {
        variant.softDeleteByParent()
        assertTrue(variant.isDeleted())
        assertTrue(variant.deletedByParent)
    }

    @Test
    fun `should return true as it was deleted independently`() {
        variant.softDelete()
        assertTrue(variant.wasDeletedIndependently())
    }

    @Test
    fun `should restore deleted variant`() {
        variant.softDelete()
        variant.restore()
        assertFalse(variant.isDeleted())
        assertFalse(variant.deletedByParent)
    }
}
