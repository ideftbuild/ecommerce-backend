package com.ideftbuild.ecommerce_backend.product.domain

import com.ideftbuild.ecommerce_backend.category.domain.Category
import com.ideftbuild.ecommerce_backend.product.domain.model.Money
import com.ideftbuild.ecommerce_backend.product.domain.model.Product
import com.ideftbuild.ecommerce_backend.product.domain.model.ProductStatus
import com.ideftbuild.ecommerce_backend.product.domain.model.Variant
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.time.Instant
import java.util.Currency
import java.util.Locale
import java.util.Locale.getDefault
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
            name = "Chair",
            description = "Modern luxurious wooden chair",
            currency = Currency.getInstance("USD"),
            variants = mutableListOf(
                Variant(
                    id = UUID.randomUUID(),
                    sku = "PROD-CHAIR-6666",
                    price = Money.of(BigDecimal("200"), Currency.getInstance("USD")),
                    quantity = 8,
                ),
                Variant(
                    id = UUID.randomUUID(),
                    sku = "PROD-CHAIR-7777",
                    price = Money.of(BigDecimal("400"), Currency.getInstance("USD")),
                    quantity = 2,
                )
            ),
            category = Category(
                id = UUID.randomUUID(),
                name = "Test Cloths",
                description = "Test Cloths category",
                slug = "test-cloths",
            )
        )
    }

    @Test
    fun `should create product successfully`() {
        assertEquals("Chair", product.name)
        assertEquals("Modern luxurious wooden chair", product.description)
        assertFalse(product.isDeleted())
    }

    @Test
    fun `should delete product and its variants`() {
        product.softDelete()

        product.variants.forEach {
            assertTrue(it.isDeleted())
        }
        assertEquals(product.variantsCache, null)
        assertTrue(product.isDeleted())
    }

    @Test
    fun `should not delete deleted product`() {
        product.deletedAt = Instant.now()
        assertThrows<IllegalArgumentException> { product.softDelete() }
    }

    @Test
    fun `should restore deleted product`() {
        product.softDelete()
        product.restore()
        assertFalse(product.isDeleted())

        product.variants.forEach {
            assertFalse(it.isDeleted())
        }
        assertEquals(product.variantsCache, null)
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

    @Test
    fun `should add variants`() {
        val variant = Variant(
            sku = "TEST-TEST-TEST",
            price = Money.of(BigDecimal("100"), Currency.getInstance("USD")),
            productId = product.id,
            attributes = mapOf(
                "color" to "black",
                "size" to "M"
            ),
            quantity = 100
        )
        product.addVariant(variant)
        assertEquals(product.variants.elementAt(0), variant)
    }

    @Test
    fun `should update fields`() {
        val currency = "eur"
        product.update(
            name = "test",
            description = "test description",
            currency = currency
        )

        assertEquals(product.name, "test")
        assertEquals(product.description, "test description")
        assertEquals(product.currency.currencyCode, currency.uppercase(Locale.ROOT))
    }

    @Test
    fun `should update fields partially`() {
        product.update(
            name = "test",
            description = "test description",
        )

        assertEquals(product.name, "test")
        assertEquals(product.description, "test description")
        assertEquals(product.currency, product.currency)
    }

    @Test
    fun `should throw exception when only one variant exists`()  {
        val variant1Id = product.activeVariants[0].id!!
        val variant2Id = product.activeVariants[1].id!!

        product.removeVariant(variant1Id)
        assertThrows<IllegalArgumentException> { product.removeVariant(variant2Id) }
    }

    @Test
    fun `should throw exception when variant is not found `()  {
        assertThrows<ResourceNotFoundException> { product.removeVariant(UUID.randomUUID()) }
    }

    @Test
    fun `should remove variant from product`()  {
        val variant = product.activeVariants[0]
        product.removeVariant(variant.id!!)

        assertTrue(variant.isDeleted())
        assertFalse(variant.deletedByParent)
    }
}
