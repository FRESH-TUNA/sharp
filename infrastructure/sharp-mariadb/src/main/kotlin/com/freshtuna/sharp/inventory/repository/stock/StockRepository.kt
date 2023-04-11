package com.freshtuna.sharp.inventory.repository.stock

import com.freshtuna.sharp.inventory.entity.MariaDBStock
import com.freshtuna.sharp.inventory.entity.MariaDBStockInfo
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<MariaDBStock, Long> {

    fun findAllByInfoIdIn(infos: List<Long>): List<MariaDBStock>

    fun findAllByInfoIn(infos: List<MariaDBStockInfo>): List<MariaDBStock>
}
