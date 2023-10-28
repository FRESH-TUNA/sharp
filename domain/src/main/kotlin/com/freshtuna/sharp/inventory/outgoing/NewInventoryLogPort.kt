package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog

interface NewInventoryLogPort {

    fun new(command: InventoryCommand, skuId: SharpID): InventoryLog
}
