package com.freshtuna.sharp.inventory.command

import com.freshtuna.sharp.id.SharpID

class UpdateStockInfoCommend(
    val infoId: SharpID,
    val newStockCount: Long
)
