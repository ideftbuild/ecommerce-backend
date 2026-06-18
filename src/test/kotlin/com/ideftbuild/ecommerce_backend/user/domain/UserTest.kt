package com.ideftbuild.ecommerce_backend.user.domain

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class UserTest {

    private lateinit var user: User

    @BeforeEach
    fun setUp() {
        this.user = User(
            id = UUID.randomUUID(),
            username = "testUser",
            firstName = "testFirstName",
            lastName = "testLastName",
            email = "test@email.com",
            password = "password",
            roles = mutableSetOf(),
        )
    }

    @Test
    fun `should soft delete user`() {
        user.softDelete()
        assertTrue(user.deletedAt != null)
    }

    @Test
    fun `should throw exception deleting already deleted user`() {
        user.softDelete()
        assertThrows<IllegalArgumentException> { user.softDelete() }
    }

    @Test
    fun `should throw exception restoring active user`() {
        assertThrows<IllegalArgumentException> { user.restore() }
    }

    @Test
    fun `should restore deleted user`() {
        user.softDelete()
        user.restore()
        assertEquals(user.deletedAt, null)
    }
}
