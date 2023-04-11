package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.Stock
import com.freshtuna.sharp.inventory.StockInfo

interface SearchStockPort {

    fun findAll(stockInfos: List<StockInfo>): List<Stock>
}
