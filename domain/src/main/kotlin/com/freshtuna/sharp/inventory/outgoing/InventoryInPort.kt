package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand

interface InventoryInPort {

    fun `in`(command: InventoryCommand, skuId: SharpID)
}
