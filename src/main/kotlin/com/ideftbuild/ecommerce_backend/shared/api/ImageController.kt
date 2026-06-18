package com.ideftbuild.ecommerce_backend.shared.api

import com.ideftbuild.ecommerce_backend.shared.application.port.output.ImageStoragePort
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/images")
class ImageController(
    private val storage: ImageStoragePort
) {

    @PostMapping(value = ["/images"], consumes = ["multipart/form-data"])
    fun upload(@RequestParam files: List<MultipartFile>): List<String> {
        return files.map { file ->
            val key = storage.upload(
                file.bytes,
                file.originalFilename ?: "file",
                file.contentType ?: "application/octet-stream"
            )

            storage.getUrl(key)
        }
    }

    @DeleteMapping("/{name}")
    fun delete(@PathVariable name: String) {
        storage.delete(name)
    }
}
