package com.freshtuna.sharp.product.repository

import com.freshtuna.sharp.product.entity.MariaDBProductOption
import org.springframework.data.jpa.repository.JpaRepository

interface ProductOptionRepository : JpaRepository<MariaDBProductOption, Long> {

    fun deleteAllByProductId(productId: Long)
}
