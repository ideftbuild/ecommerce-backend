package com.ideftbuild.ecommerce_backend.user.domain

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RoleTest {
    private lateinit var role: Role

    @BeforeEach
    fun setUp() {
        this.role = Role(
            id = UUID.randomUUID(),
            name = "ADMIN",
            description = "description",
        )
    }

    @Test
    fun `should soft delete user`() {
        role.softDelete()
        assertTrue(role.deletedAt != null)
    }

    @Test
    fun `should throw exception deleting already deleted role`() {
        role.softDelete()
        assertThrows<IllegalArgumentException> { role.softDelete() }
    }

    @Test
    fun `should throw exception restoring active role`() {
        assertThrows<IllegalArgumentException> { role.restore() }
    }

    @Test
    fun `should restore deleted role`() {
        role.softDelete()
        role.restore()
        assertEquals(role.deletedAt, null)
    }
}
