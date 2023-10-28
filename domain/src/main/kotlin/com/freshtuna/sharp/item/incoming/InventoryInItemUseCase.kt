package com.freshtuna.sharp.item.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand

interface InventoryInItemUseCase {

    fun new(command: InventoryCommand, itemId: SharpID, sellerId: SharpID)
}