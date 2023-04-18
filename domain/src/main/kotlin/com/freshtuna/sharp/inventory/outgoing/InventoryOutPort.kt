package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.command.NewInventoryLogCommand

interface InventoryOutPort {

    fun out(command: NewInventoryLogCommand)
}
