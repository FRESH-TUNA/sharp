package com.freshtuna.sharp.inventory.command

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.inventory.InventoryCondition
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason

class InventoryInOutCommand(

    val skuId: SharpID,

    val count: Long,

    val reason: InventoryLogReason,

    val description: String = ""
)
