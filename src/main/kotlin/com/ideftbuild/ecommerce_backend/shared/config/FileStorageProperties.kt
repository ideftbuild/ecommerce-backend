package com.ideftbuild.ecommerce_backend.shared.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import jakarta.annotation.PostConstruct
import java.nio.file.Files
import java.nio.file.Path

@Component
class FileStorageProperties(
    @param:Value($$"${file.upload-dir:uploads}")
    val uploadDir: String
) {
    lateinit var rootLocation: Path

    @PostConstruct
    fun init() {
        rootLocation = Path.of(uploadDir).toAbsolutePath().normalize()
        Files.createDirectories(rootLocation)
    }
}
