package com.freshtuna.sharp.inventory.domain.inventory.log

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.domain.inventory.InventoryCondition

class InventoryLog(
    val id: PublicId,

    val skuId: PublicId,
    val type: InventoryLogType,

    val count: Long,
    val condition: InventoryCondition,
    val reason: InventoryLogReason,

    val description: String
)
