package com.ideftbuild.ecommerce_backend.shared.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
//import software.amazon.awssdk.regions.Region
//import software.amazon.awssdk.services.s3.S3Client
//import software.amazon.awssdk.services.s3.S3Configuration
//import java.net.URI
//
//@Configuration
//class CloudflareR2Config(
//    private val properties: CloudflareR2Properties
//) {
//
//    @Bean
//    fun r2S3Client(): S3Client {
//        val credentials = AwsBasicCredentials.create(
//            properties.accessKeyId,
//            properties.secretAccessKey
//        )
//
//        return S3Client.builder()
//            .region(Region.of("auto"))  // R2 uses auto region
//            .credentialsProvider(StaticCredentialsProvider.create(credentials))
//            .endpointOverride(URI.create(properties.endpoint))
//            .serviceConfiguration(
//                S3Configuration.builder()
//                    .pathStyleAccessEnabled(false)  // R2 uses virtual-hosted-style
//                    .build()
//            )
//            .build()
//    }
//}
