package com.ideftbuild.ecommerce_backend.shared.infrastructure
//
//import com.ideftbuild.ecommerce_backend.shared.config.CloudflareR2Properties
//import com.ideftbuild.ecommerce_backend.shared.exception.ImageDeletionException
//import com.ideftbuild.ecommerce_backend.shared.exception.ImageUploadException
//import com.ideftbuild.ecommerce_backend.shared.port.output.ImageStoragePort
//import org.slf4j.LoggerFactory
//import org.springframework.stereotype.Component
//import software.amazon.awssdk.core.sync.RequestBody
//import software.amazon.awssdk.services.s3.S3Client
//import software.amazon.awssdk.services.s3.model.*
//import java.util.*
//
//@Component
//class CloudflareR2StorageAdapter(
//    private val s3Client: S3Client,
//    private val properties: CloudflareR2Properties
//) : ImageStoragePort {
//
//    private val logger = LoggerFactory.getLogger(javaClass)
//
//    override fun upload(file: ByteArray, fileName: String, contentType: String): String {
//        try {
//            // Generate unique image name
//            val imageName = generateImageName(fileName)
//
//            // Upload to R2
//            val putRequest = PutObjectRequest.builder()
//                .bucket(properties.bucketName)
//                .key(imageName)
//                .contentType(contentType)
//                .contentLength(file.size.toLong())
//                .build()
//
//            s3Client.putObject(putRequest, RequestBody.fromBytes(file))
//
//            logger.info("Successfully uploaded image: $imageName")
//            return imageName
//
//        } catch (e: Exception) {
//            logger.error("Failed to upload image: ${e.message}", e)
//            throw ImageUploadException("Failed to upload image", e)
//        }
//    }
//
//    override fun getUrl(imageName: String): String {
//        return if (properties.publicUrl.isNotBlank()) {
//            // Use custom domain if configured
//            "${properties.publicUrl.trimEnd('/')}/$imageName"
//        } else {
//            // Use R2.dev public bucket URL or generate presigned URL
//            "https://pub-${properties.accountId}.r2.dev/$imageName"
//        }
//    }
//
//    override fun delete(imageName: String) {
//        try {
//            val deleteRequest = DeleteObjectRequest.builder()
//                .bucket(properties.bucketName)
//                .key(imageName)
//                .build()
//
//            s3Client.deleteObject(deleteRequest)
//            logger.info("Successfully deleted image: $imageName")
//
//        } catch (e: Exception) {
//            logger.error("Failed to delete image: ${e.message}", e)
//            throw ImageDeletionException("Failed to delete image", e)
//        }
//    }
//
//    override fun exists(imageName: String): Boolean {
//        return try {
//            val headRequest = HeadObjectRequest.builder()
//                .bucket(properties.bucketName)
//                .key(imageName)
//                .build()
//
//            s3Client.headObject(headRequest)
//            true
//
//        } catch (e: NoSuchKeyException) {
//            false
//        } catch (e: Exception) {
//            logger.error("Failed to check image existence: ${e.message}", e)
//            false
//        }
//    }
//
//    /**
//     * Generate unique image name with timestamp and UUID
//     */
//    private fun generateImageName(originalFileName: String): String {
//        val extension = originalFileName.substringAfterLast('.', "")
//        val timestamp = System.currentTimeMillis()
//        val uuid = UUID.randomUUID().toString().substring(0, 8)
//
//        return if (extension.isNotBlank()) {
//            "images/${timestamp}_${uuid}.$extension"
//        } else {
//            "images/${timestamp}_${uuid}"
//        }
//    }
//}
