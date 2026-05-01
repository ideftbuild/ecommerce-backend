package com.ideftbuild.ecommerce_backend.shared.exception


class ImageUploadException(
    message: String,
    cause: Throwable? = null
) : BusinessException(message, cause)

class ImageDeletionException(
    message: String,
    cause: Throwable? = null
) : BusinessException(message, cause)

class ImageNotFoundException(
    imageName: String
) : ResourceNotFoundException("Image not found: $imageName")
