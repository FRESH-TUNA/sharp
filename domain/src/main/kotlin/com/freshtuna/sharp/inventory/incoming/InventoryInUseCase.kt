package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.InventoryInOutCommand

interface InventoryInUseCase {

    fun new(command: InventoryInOutCommand, sellerId: SharpID)
}
