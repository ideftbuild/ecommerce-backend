package com.ideftbuild.ecommerce_backend.shared.api

import com.ideftbuild.ecommerce_backend.shared.exception.ImageNotFoundException
import com.ideftbuild.ecommerce_backend.shared.exception.ImageUploadException
import com.ideftbuild.ecommerce_backend.shared.exception.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = ex.bindingResult.fieldErrors.associate {
            it.field to (it.defaultMessage ?: "Invalid value")
        }

        return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponse(
            message = "Validation failed",
            errors = errors))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponse(
            message = ex.message ?: "Business rule violation",
            errors = null
        ))
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(
                errors = null,
                message = ex.message ?: "Resource Not Found"
            ))
    }

    @ExceptionHandler(ImageUploadException::class)
    fun handleImageUploadException(ex: ImageUploadException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(
                errors = mapOf(
                    "status" to "404",
                    "timestamp" to "${Instant.now()}",
                    "error" to "Image Upload Failed"
                ),
                message = ex.message ?: "Failed to upload image"
            ))
    }

    @ExceptionHandler(ImageNotFoundException::class)
    fun handleImageNotFoundException(ex: ImageNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(
                errors = mapOf(
                    "status" to "404",
                    "timestamp" to "${Instant.now()}",
                    "error" to "Image Not Found"
                ),
                message = ex.message ?: "Image not found"
            ))
    }
}

