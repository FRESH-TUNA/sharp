package com.freshtuna.sharp.inventory.repository.stock

import com.freshtuna.sharp.inventory.entity.MariaDBStock
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<MariaDBStock, Long>
