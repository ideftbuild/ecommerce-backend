package com.ideftbuild.ecommerce_backend.product.domain.model

import java.math.BigDecimal

data class ProductQuery(
    val name: String? = null,
    val minPrice: BigDecimal? = null,
    val maxPrice: BigDecimal? = null
)
