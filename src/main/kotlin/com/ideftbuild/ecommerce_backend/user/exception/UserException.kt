package com.ideftbuild.ecommerce_backend.user.exception

import com.ideftbuild.ecommerce_backend.shared.exception.BusinessException

class UserCreationException(
    message: String,
    cause: Throwable? = null
) : BusinessException(message, cause)
