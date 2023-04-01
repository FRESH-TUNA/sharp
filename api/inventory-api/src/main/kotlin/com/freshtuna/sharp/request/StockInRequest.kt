package com.freshtuna.sharp.request

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.StockInCommand

class StockInRequest(
    private val skuId: String,
    private val count: Long
) {
    fun toCommand(sellerId: PublicId)
        = StockInCommand(sellerId, PublicId(skuId), count)
}
