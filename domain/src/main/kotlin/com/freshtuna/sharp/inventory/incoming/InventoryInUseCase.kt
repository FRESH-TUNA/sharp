package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.NewInventoryLogCommand

interface InventoryInUseCase {

    fun new(command: NewInventoryLogCommand, sellerId: PublicId)
}
