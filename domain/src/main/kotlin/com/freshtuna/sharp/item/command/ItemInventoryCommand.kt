package com.freshtuna.sharp.item.command

import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason

class ItemInventoryCommand(
    val count: Long,
    val reason: InventoryLogReason,
    val description: String = ""
) {
}

