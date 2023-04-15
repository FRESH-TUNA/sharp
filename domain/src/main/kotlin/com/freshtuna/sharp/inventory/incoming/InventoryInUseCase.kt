package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.NewInventoryCommand

interface InventoryInUseCase {

    fun new(command: NewInventoryCommand, sellerId: PublicId)
}
