package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.user.api.dto.PermissionResponse
import com.ideftbuild.ecommerce_backend.user.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetPermissionInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.PermissionOutPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetPermissionUseCase(
    private val permissionOutPort: PermissionOutPort
): GetPermissionInputPort {
    override fun execute(id: UUID): PermissionResponse {
       val permission = permissionOutPort.findById(id)
           ?: throw ResourceNotFoundException("Permission", id)

        return permission.toResponse()
    }
}
