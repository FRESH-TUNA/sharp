package com.freshtuna.sharp.inventory.repository.stock

import com.freshtuna.sharp.inventory.entity.MariaDBStockInfo
import org.springframework.data.jpa.repository.JpaRepository

interface StockInfoRepository : JpaRepository<MariaDBStockInfo, Long> {
}