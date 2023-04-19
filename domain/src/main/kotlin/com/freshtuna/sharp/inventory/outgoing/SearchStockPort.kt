package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.domain.Stock
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog

interface SearchStockPort {

    fun findAll(inventories: List<InventoryLog>): List<Stock>
}
