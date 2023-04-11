package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.StockInfo

interface FindStockInfoPort {

    fun find(infoId: PublicId): StockInfo
}
