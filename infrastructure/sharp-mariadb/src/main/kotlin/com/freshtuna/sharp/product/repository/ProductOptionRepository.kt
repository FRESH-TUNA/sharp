package com.freshtuna.sharp.product.repository

import com.freshtuna.sharp.product.entity.ProductOptionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductOptionRepository : JpaRepository<ProductOptionEntity, Long> {

    fun deleteAllByProductId(productId: Long)
}
