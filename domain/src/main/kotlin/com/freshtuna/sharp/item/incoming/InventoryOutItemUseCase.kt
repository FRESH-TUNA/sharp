package com.freshtuna.sharp.item.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand

interface InventoryOutItemUseCase {

    fun out(command: InventoryCommand, itemId: SharpID, sellerId: SharpID)
}
