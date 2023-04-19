package com.freshtuna.sharp.inventory.repository.inventory

import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.entity.MariaDBInventoryLog
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface InventoryLogRepository : JpaRepository<MariaDBInventoryLog, Long> {

    fun findAllBySku(sku: MariaDBSKU, pageable: Pageable): Page<MariaDBInventoryLog>
}
