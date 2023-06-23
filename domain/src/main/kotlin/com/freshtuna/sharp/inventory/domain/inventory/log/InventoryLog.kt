package com.freshtuna.sharp.inventory.domain.inventory.log

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.inventory.InventoryCondition

class InventoryLog(
    val id: SharpID,

    val skuId: SharpID,
    val type: InventoryLogType,

    val count: Long,
    val reason: InventoryLogReason,

    val description: String
)
