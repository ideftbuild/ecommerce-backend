package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.application.port.input.DeleteUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.UserOutputPort
import com.ideftbuild.ecommerce_backend.user.domain.User
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeleteUserUseCase (
    private val userOutputPort: UserOutputPort
): DeleteUserInputPort {
    private fun deleteUser(user: User) {
        user.softDelete()

        userOutputPort.save(user)
    }
    override fun execute(id: UUID) {
        val user = userOutputPort.findById(id)
            ?: throw ResourceNotFoundException("user", id)

        deleteUser(user)
    }

    override fun execute(username: String) {
        val user = userOutputPort.findByUsername(username)
            ?: throw ResourceNotFoundException("user", message = username)

        deleteUser(user)
    }
}
