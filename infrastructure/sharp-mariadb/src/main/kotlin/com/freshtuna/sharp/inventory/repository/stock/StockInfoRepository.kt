package com.freshtuna.sharp.inventory.repository.stock

import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.entity.MariaDBStockInfo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface StockInfoRepository : JpaRepository<MariaDBStockInfo, Long> {

    fun findAllBySku(sku: MariaDBSKU, pageable: Pageable): Page<MariaDBStockInfo>
}
