package com.freshtuna.sharp.inventory.command

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.domain.InventoryStatus

class NewInventoryCommand(

    val skuId: PublicId,
    val count: Long,
    val status: InventoryStatus
)
