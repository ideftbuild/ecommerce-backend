package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.api.dto.VariantImageResponse
import com.ideftbuild.ecommerce_backend.product.api.mapper.toVariantImageResponse
import com.ideftbuild.ecommerce_backend.product.application.port.input.UploadImageInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.VariantOutputPort
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.shared.application.port.output.ImageStoragePort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class UploadImageUseCase (
    private val storage: ImageStoragePort,
    private val variantOutputPort: VariantOutputPort
): UploadImageInputPort
{
    override fun execute(variantId: UUID, files: List<MultipartFile>): List<VariantImageResponse> {
        var variant = variantOutputPort.findById(variantId)
            ?: throw ResourceNotFoundException("variant", variantId)

        files.forEachIndexed { index, file ->
            val key = storage.upload(
                file.bytes,
                file.originalFilename ?: "file",
                file.contentType ?: "application/octet-stream"
            )

           variant.uploadImage(key,
               storage.getUrl(key),
               file.originalFilename)
       }
        variant = variantOutputPort.save(variant)

        return variant.images.map { image ->
            image.toVariantImageResponse()
        }
    }
}
