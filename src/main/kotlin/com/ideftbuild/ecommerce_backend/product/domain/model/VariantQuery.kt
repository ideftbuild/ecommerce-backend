package com.ideftbuild.ecommerce_backend.product.domain.model

data class VariantQuery(
   val sku: String? = null,
   val quantity: Long? = null
) {
    init {
        if (quantity != null) {
            require(quantity > 0) { "Quantity must be greater than 1 if provided"}
        }
    }
}
