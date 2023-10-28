package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand

interface InventoryOutPort {

    fun out(command: InventoryCommand, skuID: SharpID)
}
