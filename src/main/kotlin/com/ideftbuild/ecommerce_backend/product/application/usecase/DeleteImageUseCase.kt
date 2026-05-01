package com.ideftbuild.ecommerce_backend.product.application.usecase

import com.ideftbuild.ecommerce_backend.product.application.port.input.DeleteImageInputPort
import com.ideftbuild.ecommerce_backend.product.application.port.output.VariantOutputPort
import com.ideftbuild.ecommerce_backend.product.domain.model.VariantImage
import com.ideftbuild.ecommerce_backend.product.domain.model.removeFirstMatching
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import com.ideftbuild.ecommerce_backend.shared.port.output.ImageStoragePort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeleteImageUseCase (
    private val storage: ImageStoragePort,
    private val variantOutputPort: VariantOutputPort
): DeleteImageInputPort {
    override fun execute(variantId: UUID, name: String) {
        val variant = variantOutputPort.findById(variantId)
            ?: throw ResourceNotFoundException("variant", variantId)

        val image = variant.images.removeFirstMatching { it.originalName == name }
            ?: throw ResourceNotFoundException("Image Not Found", variantId)

        storage.delete(image.storageKey)
        variantOutputPort.save(variant)
    }

}

