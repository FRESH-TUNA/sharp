package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.NewInventoryCommand

interface InventoryOutUseCase {

    fun new(command: NewInventoryCommand, sellerId: PublicId)
}
