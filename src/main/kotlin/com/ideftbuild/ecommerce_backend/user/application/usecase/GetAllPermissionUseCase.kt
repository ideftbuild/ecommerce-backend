package com.ideftbuild.ecommerce_backend.user.application.usecase

import com.ideftbuild.ecommerce_backend.user.api.dto.PermissionResponse
import com.ideftbuild.ecommerce_backend.user.api.mapper.toResponse
import com.ideftbuild.ecommerce_backend.user.application.port.input.GetAllPermissionsInputPort
import com.ideftbuild.ecommerce_backend.user.application.port.output.PermissionOutPort
import org.springframework.stereotype.Service

@Service
class GetAllPermissionUseCase(
    private val permissionOutPort: PermissionOutPort
): GetAllPermissionsInputPort {
    override fun execute(): List<PermissionResponse> {
        val permissions = permissionOutPort.findAll()
        return permissions.map { it.toResponse() }
    }
}
