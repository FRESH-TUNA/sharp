package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand

interface InventoryOutUseCase {

    fun out(command: InventoryCommand, skuID: SharpID, sellerId: SharpID)
}
