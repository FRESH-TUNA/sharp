package com.freshtuna.sharp.product.repository

import com.freshtuna.sharp.product.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<ProductEntity, Long> {
}
