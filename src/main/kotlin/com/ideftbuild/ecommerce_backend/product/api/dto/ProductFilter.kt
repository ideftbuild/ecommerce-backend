package com.ideftbuild.ecommerce_backend.product.api.dto

import java.math.BigDecimal

data class ProductFilter(
    val name: String? = null,
    val minPrice: BigDecimal? = null,
    val maxPrice: BigDecimal? = null
)
