package com.ideftbuild.ecommerce_backend.user.application.port.input

import com.ideftbuild.ecommerce_backend.user.api.dto.PermissionResponse

interface GetAllPermissionsInputPort {
    fun execute(): List<PermissionResponse>
}
