package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog

interface FindStockInfoPort {

    fun find(infoId: SharpID): InventoryLog
}
