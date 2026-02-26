package com.ideftbuild.ecommerce_backend.product.domain

import com.ideftbuild.ecommerce_backend.product.domain.model.Money
import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import com.ideftbuild.ecommerce_backend.product.domain.model.ProductStatus
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.time.Instant
import java.util.Currency
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ProductTest {

    private lateinit var product: Product

    @BeforeEach
    fun setUp() {
        product = Product.create(
            id = UUID.randomUUID(),
            name = "Chair",
            description = "Modern luxurious wooden chair",
            price = Money.of(BigDecimal("100000000"), Currency.getInstance("USD")),
            quantity = 2
        )
    }

    @Test
    fun `should create product successfully`() {
        assertEquals("Chair", product.name)
        assertEquals("Modern luxurious wooden chair", product.description)
        assertEquals(Money.of(BigDecimal("100000000"), Currency.getInstance("USD")).amount, product.price.amount
        )
        assertEquals(2, product.quantity)
        assertFalse(product.isDeleted())
    }

    @Test
    fun `should delete product`() {
        product.softDelete()
        assertTrue(product.isDeleted())
    }

    @Test
    fun `should not delete deleted product`() {
        product.deletedAt = Instant.now()
        assertThrows<IllegalArgumentException> { product.softDelete() }
    }

    @Test
    fun `should restore deleted product`() {
        assertFalse(product.isDeleted())
    }

    @Test
    fun `should not restore non deleted product`() {
        product.deletedAt = null
        assertThrows<IllegalArgumentException> { product.restore() }
    }

    @Test
    fun `should mark product as active`() {
        assertEquals(ProductStatus.ACTIVE, product.status)
    }

    @Test
    fun `should not activate active product`() {
        assertThrows<IllegalArgumentException> { product.activate() }
    }

    @Test
    fun `should not deactivate inactive product`() {
        product.deactivate()
        assertThrows<IllegalArgumentException> { product.deactivate() }
    }
}
