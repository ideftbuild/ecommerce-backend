package com.ideftbuild.ecommerce_backend.shared.application.port.output

interface ImageStoragePort {
    /**
     * Upload image and return the image key/name
     */
    fun upload(file: ByteArray, fileName: String, contentType: String): String

    /**
     * Get public URL for an image
     */
    fun getUrl(imageName: String): String

    /**
     * Delete image by name
     */
    fun delete(imageName: String)

    /**
     * Check if image exists
     */
    fun exists(imageName: String): Boolean
}
