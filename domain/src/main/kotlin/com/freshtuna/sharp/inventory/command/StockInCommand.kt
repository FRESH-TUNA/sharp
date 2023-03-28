package com.freshtuna.sharp.inventory.command

import com.freshtuna.sharp.id.PublicId

class StockInCommand(
    val skuId: PublicId,
    val count: Long
)
