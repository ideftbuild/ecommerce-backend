package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.application.port.input.RestoreUserInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.UserOutputPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RestoreUserUseCase(
    private val userOutputPort: UserOutputPort
): RestoreUserInputPort {
    override fun execute(id: UUID) {
        val user = userOutputPort.findByIdIncludingDeleted(id)
            ?: throw ResourceNotFoundException("user", id)

        user.restore()

        userOutputPort.save(user)
    }
}
