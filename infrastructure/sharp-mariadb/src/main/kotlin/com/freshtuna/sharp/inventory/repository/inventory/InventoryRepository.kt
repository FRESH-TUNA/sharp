package com.freshtuna.sharp.inventory.repository.inventory

import com.freshtuna.sharp.inventory.domain.inventory.InventoryCondition
import com.freshtuna.sharp.inventory.domain.inventory.InventoryStatus
import com.freshtuna.sharp.inventory.entity.MariaDBInventory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface InventoryRepository : JpaRepository<MariaDBInventory, Long> {

    fun findByConditionAndStatus(condition: InventoryCondition,
                                 status: InventoryStatus,
                                 pageable: Pageable) : Page<MariaDBInventory>
}
