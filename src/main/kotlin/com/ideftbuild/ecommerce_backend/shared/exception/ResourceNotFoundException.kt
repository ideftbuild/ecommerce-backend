package com.ideftbuild.ecommerce_backend.shared.exception

import java.util.UUID

class ResourceNotFoundException (
    name: String,
    id: UUID
): BusinessException("$name with id $id not found")
