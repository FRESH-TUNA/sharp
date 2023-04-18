package com.freshtuna.sharp.inventory.command

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.domain.inventory.InventoryCondition
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason

class NewInventoryLogCommand(

    val skuId: PublicId,

    val count: Long,

    val condition: InventoryCondition,

    val reason: InventoryLogReason,

    val description: String = ""
)
