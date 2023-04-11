package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.PublicId

interface DeleteStockInfoPort {

    fun delete(infoId: PublicId)
}
