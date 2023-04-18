package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.command.NewInventoryLogCommand

interface InventoryInPort {

    fun new(command: NewInventoryLogCommand)
}