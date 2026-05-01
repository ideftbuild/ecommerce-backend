package com.ideftbuild.ecommerce_backend.shared.infrastructure

import com.ideftbuild.ecommerce_backend.shared.config.FileStorageProperties
import com.ideftbuild.ecommerce_backend.shared.port.output.ImageStoragePort
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.util.*

import java.nio.file.*

@Service
class LocalImageStorageAdapter(
    private val properties: FileStorageProperties
) : ImageStoragePort {

    override fun upload(file: ByteArray, fileName: String, contentType: String): String {
        val extension = extractExtension(fileName)
        val storedFileName = "${UUID.randomUUID()}$extension"

        val targetPath = properties.rootLocation.resolve(storedFileName)

        Files.write(
            targetPath,
            file,
            StandardOpenOption.CREATE_NEW
        )

        return storedFileName
    }

    override fun getUrl(imageName: String): String {
        return "/files/$imageName"
    }

    override fun delete(imageName: String) {
        val path = properties.rootLocation.resolve(imageName)

        Files.deleteIfExists(path)
    }

    override fun exists(imageName: String): Boolean {
        val path = properties.rootLocation.resolve(imageName)
        return Files.exists(path)
    }

    private fun extractExtension(fileName: String): String {
        val dotIndex = fileName.lastIndexOf(".")
        return if (dotIndex != -1) fileName.substring(dotIndex) else ""
    }
}
