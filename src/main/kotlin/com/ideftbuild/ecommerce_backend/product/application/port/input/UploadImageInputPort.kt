package com.ideftbuild.ecommerce_backend.product.application.port.input

import com.ideftbuild.ecommerce_backend.product.api.dto.VariantImageResponse
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

interface UploadImageInputPort {
    fun execute(variantId: UUID, files: List<MultipartFile>): List<VariantImageResponse>
}
