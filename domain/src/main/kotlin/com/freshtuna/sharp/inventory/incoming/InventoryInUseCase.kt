package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryCommand

interface InventoryInUseCase {

    fun new(command: InventoryCommand, skuId: SharpID, sellerId: SharpID)
}
