package com.freshtuna.sharp.product.repository

import com.freshtuna.sharp.product.entity.MariaDBProduct
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<MariaDBProduct, Long> {
}
