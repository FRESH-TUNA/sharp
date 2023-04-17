package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.command.NewInventoryCommand
import com.freshtuna.sharp.inventory.domain.Inventory

interface NewInventoryPort {

    fun new(command: NewInventoryCommand): Inventory
}
