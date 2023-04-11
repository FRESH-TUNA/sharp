package com.freshtuna.sharp.inventory.command

import com.freshtuna.sharp.id.PublicId

class UpdateStockInfoCommend(
    val infoId: PublicId,
    val newStockCount: Long
)
