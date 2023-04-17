package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.domain.Stock
import com.freshtuna.sharp.inventory.domain.Inventory

interface SearchStockPort {

    fun findAll(inventories: List<Inventory>): List<Stock>
}
